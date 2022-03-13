package com.example.demo.services;

import com.example.demo.entities.Book;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    List<Order> findAll();

    List<Order> findAllByCustomerId(Long id);
    List<Order> findAllByCustomerIdAndStatus(Long id, String status);


    Order findById(Long id);

    Order create(Customer customer, Book book);

    Order returnBook(Long orderId);

}
