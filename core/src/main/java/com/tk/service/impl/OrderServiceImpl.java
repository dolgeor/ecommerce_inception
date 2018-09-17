package com.tk.service.impl;

import com.tk.model.Customer;
import com.tk.model.order.Item;
import com.tk.model.order.Order;
import com.tk.repository.ItemRepository;
import com.tk.repository.OrderRepository;
import com.tk.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public List<Order> findByCustomerId(Long id) {
        return orderRepository.findByCustomerId(id);
    }

    @Override
    public Order findByCustomerIdAndEditableOrderAndAddresses(Long id, Long billingAddressId, Long shippingAddressId) {
        return orderRepository.findByCustomerIdAndEditableOrderAndAddresses(id, billingAddressId, shippingAddressId);
    }

    @Override
    public Order findByOrderId(String orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    @Override
    public List<Order> findByDate(Date date) {
        return orderRepository.findByDateBetween(date, new Date(date.getTime() + 86400_000));
    }

    @Override
    public List<Order> findAll() {
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public Order insert(Order order) {
        modifyOrderId(order);
        return orderRepository.save(order);
    }

    @Override
    public void makeUneditable(Order orderToUpdate) {
        orderToUpdate.setEditable(false);
        update(orderToUpdate);
    }

    private void modifyOrderId(Order order) {
        Customer customer = order.getCustomer();
        if (customer == null)
            return;
        if (order.getOrderId() == null) {
            order.setOrderId(generateOrderIdByCustomerDetails(customer));
        }
    }

    private String generateOrderIdByCustomerDetails(Customer customer) {
        return customer.getName() + customer.getAge() + new Date().getTime();
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public List<Item> findItemsByOrderId(Long id) {
        return itemRepository.findByOrderId(id);
    }

    @Override
    public List<Order> findOrdersByProductId(Long id) {
        List<Item> itemsContainingProduct = itemRepository.findByProductId(id);
        List<Order> orders = new ArrayList<>();
        itemsContainingProduct.forEach(item -> orders.add(item.getOrder()));
        return orders;
    }
}
