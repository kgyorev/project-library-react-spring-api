package com.example.demo.controllers;


import com.example.demo.entities.Book;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Order;
import com.example.demo.services.BookService;
import com.example.demo.services.CustomerService;
import com.example.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/nuggets")
//@CrossOrigin(origins = "http://localhost:63343")
@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "http://87.246.58.64:3000")
public class OrderController {


    private final OrderService orderService;
    private final CustomerService customerService;
    private final BookService bookService;
    @Autowired
    public OrderController(OrderService orderService, CustomerService customerService, BookService bookService) {

        this.orderService = orderService;
        this.customerService = customerService;
        this.bookService = bookService;
    }

   @GetMapping(value="/orders/all",produces = "application/json")
   @ResponseBody
   public List<Order> allList() {
       List<Order> all = this.orderService.findAll();
       return all;
    }
    @GetMapping(value="/orders/customer",produces = "application/json")
    @ResponseBody
    public List<Order> allListForCustomer(@RequestParam("id") Long idCustomer) {
        List<Order> all = this.orderService.findAllByCustomerId(idCustomer);
        return all;
    }

    @GetMapping(value="/orders/customer/status",produces = "application/json")
    @ResponseBody
    public List<Order> allListForCustomerByStatus(@RequestParam("id") Long idCustomer,@RequestParam("status") String status ) {


        List<Order> all = this.orderService.findAllByCustomerIdAndStatus(idCustomer,status);
        return all;
    }

    @GetMapping(value="/orders/customer/{id}",produces = "application/json")
    @ResponseBody
    public List<Order> one(@PathVariable("id") Long id) {

        Customer one = this.customerService.findById(id);
        List<Order> allForCustomer = this.orderService.findAllByCustomerId(id);
        return allForCustomer;
    }

    @PostMapping(value="/orders/add",produces = "application/json")
    @ResponseBody
    public Order add(
            @RequestParam("id") Long idBook,
            @RequestParam("fnumber") String fnumber) {

        Book book = this.bookService.findById(idBook);
        Customer customer = this.customerService.findByFnumber(fnumber);

        Order one = this.orderService.create(customer, book);
        return one;

    }
    @PostMapping(value="/orders/return",produces = "application/json")
    @ResponseBody
    public Order returnBook(
            @RequestParam("id") Long idOrder) {
        Order one = this.orderService.returnBook(idOrder);
        return one;

    }
}
