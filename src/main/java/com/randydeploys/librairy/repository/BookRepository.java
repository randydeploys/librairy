package com.randydeploys.librairy.repository;

import com.randydeploys.librairy.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
        boolean existsByIsbn(String isbn);

}