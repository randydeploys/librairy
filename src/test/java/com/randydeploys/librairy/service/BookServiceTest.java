package com.randydeploys.librairy.service;

import com.randydeploys.librairy.model.Book;
import com.randydeploys.librairy.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBooks_returnsListOfBooks() {
        List<Book> books = Arrays.asList(
            new Book(1L, "Livre 1", "Auteur 1", 10.0),
            new Book(2L, "Livre 2", "Auteur 2", 20.0)
        );
        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();

        assertEquals(2, result.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void getBookById_existingId_returnsBook() {
        Book book = new Book(1L, "Livre 1", "Auteur 1", 10.0);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Book result = bookService.getBookById(1L);

        assertEquals("Livre 1", result.getTitle());
    }

    @Test
    void getBookById_nonExistingId_throwsException() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bookService.getBookById(99L));
    }

    @Test
    void createBook_validPrice_savesBook() {
        Book book = new Book(null, "Nouveau Livre", "Auteur", 15.0);
        when(bookRepository.save(book)).thenReturn(book);

        Book result = bookService.createBook(book);

        assertEquals("Nouveau Livre", result.getTitle());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void createBook_negativePrice_throwsException() {
        Book book = new Book(null, "Livre", "Auteur", -5.0);

        assertThrows(RuntimeException.class, () -> bookService.createBook(book));
        verify(bookRepository, never()).save(any());
    }

    @Test
    void createBook_zeroPrice_throwsException() {
        Book book = new Book(null, "Livre", "Auteur", 0.0);

        assertThrows(RuntimeException.class, () -> bookService.createBook(book));
    }

    @Test
    void deleteBook_existingId_deletesBook() {
        Book book = new Book(1L, "Livre", "Auteur", 10.0);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        bookService.deleteBook(1L);

        verify(bookRepository, times(1)).delete(book);
    }

    @Test
    void deleteBook_nonExistingId_throwsException() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bookService.deleteBook(99L));
    }
}