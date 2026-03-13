package com.maxxbyte.robo_dash.controllers;

import com.maxxbyte.robo_dash.data.LocationDao;
import com.maxxbyte.robo_dash.data.OrderDao;
import com.maxxbyte.robo_dash.data.ProductDao;
import com.maxxbyte.robo_dash.data.UserDao;
import com.maxxbyte.robo_dash.models.*;
import com.maxxbyte.robo_dash.models.dto.CreateOrderDto;
import com.maxxbyte.robo_dash.models.dto.CreateOrderItemDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @Operation(summary = "Create a new order for the current user")
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
            item.setPrice(product.getPrice());

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
        Order order = orderDao.getById(id);
        User user = getCurrentUser();

        if (order.getUserId() != user.getId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You cannot view this order");
        }
        return order;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("my-orders")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getMyOrders() {

        User user = getCurrentUser();

        return orderDao.getByUserId(user.getId());
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("{id}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrderStatus(@PathVariable int id, @RequestBody String status)
    {
        Order  order = orderDao.getById(id);

        if(order.getStatus() == OrderStatus.COMPLETED)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order status cannot be modified");
        }

        orderDao.updateOrderStatus(id, status);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{orderId}/items")
    @ResponseStatus(HttpStatus.CREATED)
    public void addItemToOrder(@PathVariable int orderId, @RequestBody CreateOrderItemDto dto)
    {

        User user = getCurrentUser();

        Order order = orderDao.getById(orderId);

        if (order.getUserId() != user.getId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You cannot modify this order");
        }

        if(order.getStatus() != OrderStatus.IN_PROGRESS)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order cannot be modified");
        }

        Product product = productDao.getById(dto.getProductId());

        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setQuantity(dto.getQuantity());
        orderItem.setPrice(product.getPrice());
        orderItem.setProductId(product.getProductId());

        orderDao.addItemToOrder(orderId, orderItem);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{orderId}/items/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeItemFromOrder(@PathVariable int orderId, @PathVariable int productId)
    {
        Order order = orderDao.getById(orderId);

        User user =  getCurrentUser();

        if(order.getStatus() != OrderStatus.IN_PROGRESS)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order cannot be modified");
        }

        if (order.getUserId() != user.getId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You cannot modify this order");
        }

        orderDao.removeItemFromOrder(orderId, productId);
    }

    @GetMapping("{id}/route")
    @PreAuthorize("hasRole('USER')")
    public Route getOrderRoute(@PathVariable int id) {
        Order order = orderDao.getById(id);
        User user = getCurrentUser();

        if (order.getUserId() != user.getId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You cannot view this order");
        }

        return robot.getCurrentRoute();
    }

}