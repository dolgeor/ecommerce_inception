package com.tk.service.impl;

import com.tk.model.Address;
import com.tk.model.Customer;
import com.tk.repository.AddressRepository;
import com.tk.repository.CustomerRepository;
import com.tk.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Customer> findByName(String name) {
        return customerRepository.findByNameLike(name);
    }

    @Override
    public List<Customer> findAll() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public Customer insert(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public List<Address> findAddressesByCustomerId(Long id) {
        return addressRepository.findAllByCustomerId(id);
    }

    @Override
    public void insertCustomerAddress(Address address) {
        addressRepository.save(address);
    }

    @Override
    public void updateCustomerAddress(Address address) {
        addressRepository.save(address);
    }

    @Override
    public void deleteCustomerAddress(Long addressId) {
        addressRepository.deleteById(addressId);
    }

    @Override
    public Address findAddressById(Long id) {
        return addressRepository.findById(id).get();
    }
}

