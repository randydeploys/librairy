package com.randydeploys.librairy.controller;

import com.randydeploys.librairy.model.Book;
import com.randydeploys.librairy.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookRestControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookRestController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll_returnsListOfBooks() {
        List<Book> books = Arrays.asList(
            new Book(1L, "Livre 1", "Auteur 1", 10.0),
            new Book(2L, "Livre 2", "Auteur 2", 20.0)
        );
        when(bookService.getAllBooks()).thenReturn(books);

        List<Book> result = controller.getAll();

        assertEquals(2, result.size());
        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    void getById_returnsBook() {
        Book book = new Book(1L, "Livre 1", "Auteur 1", 10.0);
        when(bookService.getBookById(1L)).thenReturn(book);

        Book result = controller.getById(1L);

        assertEquals("Livre 1", result.getTitle());
    }

    @Test
    void create_returnsCreatedBook() {
        Book book = new Book(null, "Nouveau", "Auteur", 15.0);
        when(bookService.createBook(book)).thenReturn(book);

        Book result = controller.create(book);

        assertEquals("Nouveau", result.getTitle());
        verify(bookService, times(1)).createBook(book);
    }

    @Test
    void delete_callsService() {
        doNothing().when(bookService).deleteBook(1L);

        controller.delete(1L);

        verify(bookService, times(1)).deleteBook(1L);
    }
}