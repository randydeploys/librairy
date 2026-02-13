package com.randydeploys.librairy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class BookDTO {
    
    @NotBlank(message = "Le titre est obligatoire")
    private String title;
    
    @NotBlank(message = "L'auteur est obligatoire")
    private String author;
    
    @NotNull(message = "Le prix est obligatoire")
    @Positive(message = "Le prix doit être positif")    
    private Double price;
    
    @NotBlank(message = "L'ISBN est obligatoire")
    @Pattern(regexp = "^(97[89])-\\d{1,5}-\\d{1,7}-\\d{1,7}-\\d$",
         message = "Format ISBN invalide (ex: 978-2-07-036024-1)")
    private String isbn;
    
    public BookDTO() {
    }

    public BookDTO(String title, String author, Double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }


    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
}
