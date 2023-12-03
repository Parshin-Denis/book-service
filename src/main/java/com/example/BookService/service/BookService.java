package com.example.BookService.service;

import com.example.BookService.dto.BookResponse;
import com.example.BookService.dto.UpsertBookRequest;
import com.example.BookService.mapper.BookMapper;
import com.example.BookService.model.Book;
import com.example.BookService.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheManager = "redisCacheManager")
public class BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    private final CacheManager cacheManager;

    public List<BookResponse> findAll(Pageable pageable){
        return bookMapper.BookListToResponseList(
                bookRepository.findAll(pageable).getContent()
        );
    }

    @Cacheable(value = "databaseBookByAuthorAndTitle",key = "#author + #title")
    public BookResponse findBook(String author, String title){
        return bookMapper.bookToResponse(
                bookRepository.findFirstByAuthorAndTitle(author, title)
                        .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                                "Книга \"{0}\" автора {1} не найдена", title, author)))
        );
    }

    @Cacheable(value = "databaseBooksByCategory", key = "#category")
    public List<BookResponse> findAllByCategory(String category){
        return bookMapper.BookListToResponseList(
                bookRepository.findAllByCategoryName(category)
        );
    }

    @CacheEvict(value = "databaseBooksByCategory", key = "#upsertBookRequest.category")
    public BookResponse create(UpsertBookRequest upsertBookRequest){
        return bookMapper.bookToResponse(
                bookRepository.save(
                        bookMapper.requestToBook(upsertBookRequest)
                )
        );
    }

    @CacheEvict(value = "databaseBooksByCategory", key = "#upsertBookRequest.category")
    public BookResponse update(long id, UpsertBookRequest upsertBookRequest){
        evictCache(id);
        Book newBook = bookMapper.requestToBook(upsertBookRequest);
        newBook.setId(id);
        return bookMapper.bookToResponse(bookRepository.save(newBook));
    }

    public void delete(long id){
        evictCache(id);
        bookRepository.deleteById(id);
    }

    private void evictCache(long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                        "Книга с ID {0} не найдена", id)));
        Objects.requireNonNull(cacheManager.getCache("databaseBookByAuthorAndTitle"))
                .evict(book.getAuthor() + book.getTitle());
        Objects.requireNonNull(cacheManager.getCache("databaseBooksByCategory"))
                .evict(book.getCategory().getName());
    }
}
