package com.example.demo.services;

import com.example.demo.entities.Customer;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public List<Customer> findAllByName(String name) {
        return this.customerRepository.findAllByName(name);
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public Customer findByFnumber(String fnumber) {
        return customerRepository.findAllByFnumber(fnumber);
    }

    @Override
    public Customer create(String name, String fnumber) {
        Customer customer = new Customer();
        customer.setFnumber(fnumber);
        customer.setName(name);
        return customerRepository.saveAndFlush(customer);
    }

}
