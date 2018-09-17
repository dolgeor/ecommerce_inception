package com.tk.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String addressLine;

    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;

    public Address() {
    }

    public Address(String addressLine, Customer customer) {
        this.addressLine = addressLine;
        this.customer = customer;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", addressLine='" + addressLine + '\'' +
                ", customer=" + customer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) &&
                Objects.equals(addressLine, address.addressLine) &&
                Objects.equals(customer, address.customer);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, addressLine, customer);
    }
}
