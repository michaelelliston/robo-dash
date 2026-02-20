package com.maxxbyte.robo_dash.controllers;

import com.maxxbyte.robo_dash.data.CategoryDao;
import com.maxxbyte.robo_dash.data.ProductDao;
import com.maxxbyte.robo_dash.models.Category;
import com.maxxbyte.robo_dash.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("categories")
@CrossOrigin
public class CategoriesController {
    private CategoryDao categoryDao;
    private ProductDao productDao;

    @Autowired
    public CategoriesController(CategoryDao categoryDao, ProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    @GetMapping("")
    @PreAuthorize("permitAll()")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Category> getAllCategories() {

        return categoryDao.getAll();
    }

    @GetMapping("{id}")
    @PreAuthorize("permitAll()")
    @ResponseStatus(value = HttpStatus.OK)
    public Category getCategoryById(@PathVariable int id) {

        Category category = categoryDao.getById(id);
        if (category == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
        return category;
    }

    @GetMapping("{id}/products")
    @PreAuthorize("permitAll()")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Product> getProductsByCategoryId(@PathVariable int id) {

        if (categoryDao.getById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }

        return productDao.listByCategoryId(id);
    }
}
