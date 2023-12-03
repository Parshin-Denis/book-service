package com.example.BookService.repository;

import com.example.BookService.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findFirstByAuthorAndTitle(String author, String title);

    List<Book> findAllByCategoryName(String category);
}
