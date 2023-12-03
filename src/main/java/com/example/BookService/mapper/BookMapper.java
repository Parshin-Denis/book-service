package com.example.BookService.mapper;

import com.example.BookService.dto.BookResponse;
import com.example.BookService.dto.UpsertBookRequest;
import com.example.BookService.model.Book;
import com.example.BookService.service.CategoryService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class BookMapper {

    @Autowired
    CategoryService categoryService;

    public Book requestToBook(UpsertBookRequest upsertBookRequest){
        Book book = new Book();
        book.setAuthor(upsertBookRequest.getAuthor());
        book.setTitle(upsertBookRequest.getTitle());
        book.setCategory(categoryService.getCategory(upsertBookRequest.getCategory()));
        return book;
    }

    @Mapping(source = "book.category.name", target = "category")
    public abstract BookResponse bookToResponse(Book book);

    public abstract List<BookResponse> BookListToResponseList(List<Book> books);

}
