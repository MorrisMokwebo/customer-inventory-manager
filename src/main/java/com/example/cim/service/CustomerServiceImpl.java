package com.example.cim.service;

import com.example.cim.model.Customer;
import com.example.cim.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {

        Customer existingCustomer = customerRepository.getCustomerByEmail(customer.getEmail());

        if(existingCustomer != null){
            throw new RuntimeException("Customer with email: "+customer.getEmail()+" already exist");
        }

        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> retrieveAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer retrieveCustomerById(Long customerId) {

        Optional<Customer> customer = getCustomer(customerId);

        if(customer.isEmpty()){
            throw new RuntimeException("Customer with id: "+customerId+" does not exist");
        }

        return customer.get();
    }


    @Override
    public void removeCustomerById(Long customerId) {
        Optional<Customer> customer = getCustomer(customerId);

        if(customer.isEmpty()){
            throw new RuntimeException("Customer with id: "+customerId+" does not exist");
        }

        customerRepository.deleteById(customerId);
    }

    @Override
    public Customer updateCustomerDetails(Customer customer, Long customerId) {

        Optional<Customer> existingCustomer = getCustomer(customerId);

        if(existingCustomer.isEmpty()){
            throw new RuntimeException("Customer with id: "+customerId+" does not exist");
        }

        existingCustomer.get().setFirstName(customer.getFirstName());
        existingCustomer.get().setLastName(customer.getLastName());
        existingCustomer.get().setPhoneNumber(customer.getPhoneNumber());
        existingCustomer.get().setEmail(customer.getEmail());

       return customerRepository.save(existingCustomer.get());

    }

    private Optional<Customer> getCustomer(Long customerId) {
        return customerRepository.findById(customerId);
    }

}
