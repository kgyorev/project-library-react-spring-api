package com.example.demo.services;

import com.example.demo.entities.Book;
import com.example.demo.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }


    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public Book create(String title, String author,Integer availableItems, String description) {
        Book book = new Book();
        book.setDescription(description);
        book.setAuthor(author);
        book.setAvailableItems(availableItems);
        book.setTitle(title);
        return bookRepository.saveAndFlush(book);
    }

}
