package com.maxxbyte.robo_dash.controllers;

import com.maxxbyte.robo_dash.data.ProductDao;
import com.maxxbyte.robo_dash.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RestController
@RequestMapping("products")
@CrossOrigin
public class ProductsController {
    private ProductDao productDao;

    @Autowired
    public ProductsController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("{id}")
    @PreAuthorize("permitAll()")
    public Product getById(@PathVariable int id) {
        try {
            Product product = productDao.getById(id);

            if (Objects.isNull(product)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
            }

            return product;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error");
        }
    }
}
