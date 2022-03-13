package com.example.demo.services;

import com.example.demo.entities.Book;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Order;
import com.example.demo.enums.Status;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Order> findAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public List<Order> findAllByCustomerId(Long id) {
        return this.orderRepository.findAllByCustomer_Id(id);
    }

    @Override
    public List<Order> findAllByCustomerIdAndStatus(Long id, String status) {
        Status enumStatus = Status.valueOf(status);
        return this.orderRepository.findAllByCustomer_IdAndStatus(id,enumStatus);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public Order create(Customer customer, Book book) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setBook(book);
        Integer orderedItems = book.getOrderedItems();
        book.setOrderedItems(orderedItems + 1);
        Integer availableItems = book.getAvailableItems();
        book.setAvailableItems(availableItems - 1);
        bookRepository.saveAndFlush(book);
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public Order returnBook(Long orderId) {
        Order order = this.findById(orderId);
        Book book = order.getBook();
        Integer orderedItems = book.getOrderedItems();
        book.setOrderedItems(orderedItems - 1);
        Integer availableItems = book.getAvailableItems();
        book.setAvailableItems(availableItems + 1);
        order.setStatus(Status.FINISHED);
        bookRepository.saveAndFlush(book);
        return orderRepository.saveAndFlush(order);
    }

}
