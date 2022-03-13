package com.example.demo.entities;


import com.example.demo.enums.Status;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private Status status;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Customer customer;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Book book;

    public Order() {
        this.setStatus(Status.PENDING);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
