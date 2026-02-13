package com.randydeploys.librairy;

import com.randydeploys.librairy.model.Book;
import com.randydeploys.librairy.repository.BookRepository;
import com.randydeploys.librairy.service.BookService;

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
    public Book create(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    // PUT modifier un livre
    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    // DELETE supprimer un livre
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}