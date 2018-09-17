package com.tk.service;

import com.tk.model.order.Item;
import com.tk.model.order.Order;

import java.util.Date;
import java.util.List;

public interface OrderService {

    List<Order> findAll();

    List<Order> findByCustomerId(Long id);

    Order findByCustomerIdAndEditableOrderAndAddresses(Long id, Long billingAddressId, Long shippingAddressId);

    List<Order> findByDate(Date date);

    Order findByOrderId(String orderId);

    Order findById(Long id);

    Order insert(Order order);

    Order update(Order order);

    void delete(Order order);

    void makeUneditable(Order orderToUpdate);

    public List<Item> findItemsByOrderId(Long id);

    public List<Order> findOrdersByProductId(Long id);
}
