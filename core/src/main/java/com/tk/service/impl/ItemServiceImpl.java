package com.tk.service.impl;

import com.tk.model.Product;
import com.tk.model.order.Item;
import com.tk.model.order.Order;
import com.tk.repository.ItemRepository;
import com.tk.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository itemRepository;

    @Override
    public List<Item> findByOrderId(Long id) {
        return itemRepository.findByOrderId(id);
    }

    @Override
    public List<Item> findByProductId(Long id) {
        return itemRepository.findByProductId(id);
    }

    @Override
    public Item findByOrderAndProduct(Order order, Product product) {
        return itemRepository.findByOrderAndProduct(order, product);
    }

    @Override
    public List<Item> findAll() {
        return (List<Item>) itemRepository.findAll();
    }

    @Override
    public Item findById(Long id) {
        return itemRepository.findById(id).get();
    }

    @Override
    public Item insert(Item item) {
        Item item1 = findByOrderAndProduct(item.getOrder(), item.getProduct());
        if (item1 == null)
            return itemRepository.save(item);
        item1.setQuantity(item.getQuantity() + item1.getQuantity());
        return update(item1);
    }

    @Override
    public Item update(Item item) {
        return itemRepository.save(item);
    }
    @Override
    public Item update(Item item, Long quantity) {
        if (!item.getQuantity().equals(quantity)) {
            item.setQuantity(quantity);
            return update(item);
        }
        return item;
    }
    @Override
    public void delete(Item item) {
        itemRepository.delete(item);
    }
}
