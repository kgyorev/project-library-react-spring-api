package com.example.demo.services;

import com.example.demo.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    List<Customer> findAll();
    List<Customer> findAllByName(String name);

    Customer findById(Long id);
    Customer findByFnumber(String fnumber);

    Customer create(String name, String fnumber);


}
