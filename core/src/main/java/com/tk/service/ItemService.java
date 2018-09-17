package com.tk.service;

import com.tk.model.Product;
import com.tk.model.order.Item;
import com.tk.model.order.Order;


import java.util.List;

public interface ItemService {

    List<Item> findByOrderId(Long id);

    List<Item> findByProductId(Long id);

    Item findByOrderAndProduct(Order order, Product product);

    List<Item> findAll();

    Item findById(Long id);

    Item insert(Item item);

    Item update(Item item);

    Item update(Item item, Long quantity);

    void delete(Item item);
}
