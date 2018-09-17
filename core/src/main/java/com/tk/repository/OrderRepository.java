package com.tk.repository;

import com.tk.model.order.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByCustomerId(Long id);

    @Query("select o from Order o where o.customer.id = ?1 and o.billingAddress.id = ?2 and o.shippingAddress.id = ?3 and isEditable = 1")
    Order findByCustomerIdAndEditableOrderAndAddresses(Long id, Long billingAddressId, Long shippingAddressId);

    List<Order> findByDateBetween(Date startDate, Date endDate);

    Order findByOrderId(String orderId);
}