package com.tk.service;

import com.tk.model.Address;
import com.tk.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findByName(String name);

    List<Customer> findAll();

    Customer findById(Long id);

    Customer insert(Customer customer);

    Customer update(Customer customer);

    void delete(Customer customer);

    List<Address> findAddressesByCustomerId(Long id);

    void insertCustomerAddress(Address address);

    void updateCustomerAddress(Address address);

    void deleteCustomerAddress(Long addressId);

    Address findAddressById(Long id);
}
