package com.example.cim.controller;

import com.example.cim.model.Customer;
import com.example.cim.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public Customer saveCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/all")
    public List<Customer> allCustomers(){
        return customerService.retrieveAllCustomers();
    }

    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable Long customerId){
        return customerService.retrieveCustomerById(customerId);
    }

    @DeleteMapping("/{customerId}")
    public void removeCustomer(@PathVariable Long customerId){
        customerService.removeCustomerById(customerId);
    }

    @PutMapping("/update/{customerId}")
    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable Long customerId){
        return customerService.updateCustomerDetails(customer,customerId);
    }

}
