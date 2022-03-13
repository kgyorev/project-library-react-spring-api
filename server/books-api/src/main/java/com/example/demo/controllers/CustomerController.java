package com.example.demo.controllers;


import com.example.demo.entities.Customer;
import com.example.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/nuggets")
//@CrossOrigin(origins = "http://localhost:63343")
@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "http://87.246.58.64:3000")
public class CustomerController {


    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

   @GetMapping(value="/customers/all",produces = "application/json")
   @ResponseBody
   public List<Customer> allList() {
       List<Customer> all = this.customerService.findAll();
       return all;
    }
    @GetMapping(value="/customers/details",produces = "application/json")
    @ResponseBody
    public Customer one(@RequestParam("id") Long id) {

        Customer one = this.customerService.findById(id);
        return one;

    }

    @PostMapping(value="/customers/add",produces = "application/json")
    @ResponseBody
    public Customer add(
            @RequestParam("name") String name,
            @RequestParam("fnumber") String fnumber) {
        Customer one = this.customerService.create(name, fnumber);
        return one;

    }
}
