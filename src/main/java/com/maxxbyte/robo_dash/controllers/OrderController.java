package com.maxxbyte.robo_dash.controllers;

import com.maxxbyte.robo_dash.data.LocationDao;
import com.maxxbyte.robo_dash.data.OrderDao;
import com.maxxbyte.robo_dash.data.ProductDao;
import com.maxxbyte.robo_dash.data.UserDao;
import com.maxxbyte.robo_dash.models.*;
import com.maxxbyte.robo_dash.models.dto.CreateOrderDto;
import com.maxxbyte.robo_dash.models.dto.CreateOrderItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {

    private final OrderDao orderDao;
    private final ProductDao productDao;
    private final UserDao userDao;
    private final Robot robot;

    @Autowired
    public OrderController(OrderDao orderDao, ProductDao productDao, UserDao userDao, Robot robot) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.userDao = userDao;
        this.robot = robot;
    }

    private User getCurrentUser() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userDao.getByUserName(username);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody CreateOrderDto dto)
    {
        User user = getCurrentUser();

        Order order = new Order();
        order.setUserId(user.getId());
        order.setDeliveryLocationId(dto.getDeliveryLocationId());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.IN_PROGRESS);

        double total = 0;

        for(CreateOrderItemDto itemDto : dto.getItems())
        {
            Product product = productDao.getById(itemDto.getProductId());

            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemDto.getQuantity());

            order.getItems().put(product.getProductId(), item);

            total += product.getPrice() * itemDto.getQuantity();
        }

        order.setTotalPrice(total);

        Order createdOrder = orderDao.create(order);

        robot.setCurrentOrder(createdOrder);

        return createdOrder;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order getOrderById(@PathVariable int id) {
        return orderDao.getById(id);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("my-orders")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getByUserId() {

        User user = getCurrentUser();

        return orderDao.getByUserId(user.getId());
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("{id}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrderStatus(@PathVariable int id, @RequestBody String status)
    {
        System.out.println("Status received: [" + status + "]");
        orderDao.updateOrderStatus(id, status);
    }

}