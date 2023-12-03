package com.example.BookService.controller;

import com.example.BookService.dto.BookResponse;
import com.example.BookService.dto.UpsertBookRequest;
import com.example.BookService.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @Operation(summary = "Get all books")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<BookResponse> findAll(Pageable pageable){
        return bookService.findAll(pageable);
    }

    @Operation(summary = "Create book")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BookResponse create(@RequestBody @Valid UpsertBookRequest upsertBookRequest){
        return bookService.create(upsertBookRequest);
    }

    @Operation(summary = "Get book by author and title")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/by-author-and-title")
    public BookResponse findBook(@RequestParam String author, @RequestParam String title){
        return bookService.findBook(author, title);
    }

    @Operation(summary = "Get all books by category")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/by-category")
    public List<BookResponse> findBooksByCategory(@RequestParam String category){
        return bookService.findAllByCategory(category);
    }
    
    @Operation(summary = "Modify book by ID")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public BookResponse update(@PathVariable long id, @RequestBody @Valid UpsertBookRequest upsertBookRequest){
        return bookService.update(id, upsertBookRequest);
    }

    @Operation(summary = "delete book by ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        bookService.delete(id);
    }
}
