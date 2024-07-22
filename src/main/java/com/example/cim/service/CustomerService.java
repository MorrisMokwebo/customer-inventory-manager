package com.example.cim.service;

import com.example.cim.model.Customer;
import com.example.cim.model.Item;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    List<Customer> retrieveAllCustomers();
    Customer retrieveCustomerById(Long customerId);
    void removeCustomerById(Long customerId);
    Customer updateCustomerDetails(Customer customer, Long customerId);
}
