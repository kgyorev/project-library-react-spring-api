package com.example.demo.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "description")
    private String description;

    @Column(name = "availableItems")
    private Integer availableItems;

    @Column(name = "orderedItems")
    private Integer orderedItems;

    @OneToMany(mappedBy = "book")
    @Transient
    private Set<Order> orders = new HashSet<Order>();

    public Book() {
        this.availableItems = 0;
        this.orderedItems = 0;
        this.orders = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAvailableItems() {
        return availableItems;
    }

    public void setAvailableItems(Integer availableItems) {
        this.availableItems = availableItems;
    }

    public Integer getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(Integer orderedItems) {
        this.orderedItems = orderedItems;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }


}
