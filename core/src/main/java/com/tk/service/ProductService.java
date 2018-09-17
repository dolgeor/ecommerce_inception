package com.tk.service;

import com.tk.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findByName(String name);

    List<Product> findBySku(String sku);

    List<Product> findAll();

    Product findById(Long id);

    Product insert(Product product);

    Product update(Product product);

    void delete(Product product);
}
