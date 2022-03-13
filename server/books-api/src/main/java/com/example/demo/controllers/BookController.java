package com.example.demo.controllers;


import com.example.demo.entities.Book;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/nuggets")
//@CrossOrigin(origins = "http://localhost:63343")
@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "http://87.246.58.64:3000")

public class BookController {


    private final BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

   @GetMapping(value="/books/all",produces = "application/json")
   @ResponseBody
   public List<Book> allList() {
       List<Book> all = this.bookService.findAll();
       return all;
    }
    @GetMapping(value="/books/details",produces = "application/json")
    @ResponseBody
    public Book one(@RequestParam("id") Long id) {

        Book one = this.bookService.findById(id);
        return one;

    }

    @PostMapping(value="/books/add",produces = "application/json")
    @ResponseBody
    public Book add(
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("availableItems") Integer availableItems,
            @RequestParam("description") String description) {
        Book one = this.bookService.create(title,author,availableItems,description);
        return one;

    }

}
