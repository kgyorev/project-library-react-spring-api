package com.example.demo.services;

import com.example.demo.entities.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    List<Book> findAll();
    Book findById(Long id);
    Book create(String title, String author,Integer availableItems, String description);

}
