package com.example.BookService.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookResponse implements Serializable {
    private long id;

    private String author;

    private String title;

    private String category;
}
