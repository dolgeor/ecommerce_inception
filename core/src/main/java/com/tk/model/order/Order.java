package com.tk.model.order;

import com.tk.model.Address;
import com.tk.model.Customer;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "BillingAddressId", nullable = false)
    private Address billingAddress;

    @ManyToOne
    @JoinColumn(name = "ShippingAddressId", nullable = false)
    private Address shippingAddress;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(columnDefinition = "tinyint(1) default 1")
    private Boolean isEditable;


    public Order() {
    }

    public Order(Customer customer, Address billingAddress, Address shippingAddress) {
        this.customer = customer;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.isEditable = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getEditable() {
        return isEditable;
    }

    public void setEditable(Boolean editable) {
        isEditable = editable;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(orderId, order.orderId) &&
                Objects.equals(customer, order.customer) &&
                Objects.equals(billingAddress, order.billingAddress) &&
                Objects.equals(shippingAddress, order.shippingAddress) &&
                Objects.equals(date, order.date) &&
                Objects.equals(isEditable, order.isEditable);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, orderId, customer, billingAddress, shippingAddress, date, isEditable);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", customer=" + customer +
                ", billingAddress=" + billingAddress +
                ", shippingAddress=" + shippingAddress +
                ", date=" + date +
                ", isEditable=" + isEditable +
                '}';
    }
}