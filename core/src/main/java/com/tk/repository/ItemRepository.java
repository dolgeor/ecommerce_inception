package com.tk.repository;



import com.tk.model.Product;
import com.tk.model.order.Item;
import com.tk.model.order.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findByOrderId(Long id);
    List<Item> findByProductId(Long id);
    Item findByOrderAndProduct(Order order, Product product);

}