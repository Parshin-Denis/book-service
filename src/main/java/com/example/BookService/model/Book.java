package com.example.BookService.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "books")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String author;

    private String title;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
