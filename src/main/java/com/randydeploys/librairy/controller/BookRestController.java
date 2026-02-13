package com.randydeploys.librairy.controller;

import com.randydeploys.librairy.dto.BookDTO;
import com.randydeploys.librairy.model.Book;
import com.randydeploys.librairy.repository.BookRepository;
import com.randydeploys.librairy.service.BookService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    // GET tous les livres
    @GetMapping
    public List<Book> getAll() {
        return bookService.getAllBooks();
    }

    // GET un livre par id
    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    // POST créer un livre
    @PostMapping
    public Book create(@Valid @RequestBody BookDTO dto) {
        return bookService.createBook(dto);
    }

    // PUT modifier un livre
    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody BookDTO dto) {
        return bookService.updateBook(id, dto);
    }

    // DELETE supprimer un livre
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}