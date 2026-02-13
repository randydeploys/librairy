package com.randydeploys.librairy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "books")
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le titre est obligatoire")
    @Size(min = 2, max = 255, message = "Le titre doit faire entre 2 et 255 caractères")
    private String title;

    @NotBlank(message = "L'auteur est obligatoire")
    @Size(min = 2, max = 255, message = "L'auteur doit faire entre 2 et 255 caractères")
    private String author;

    @Min(value = 0, message = "Le prix doit être supérieur à 0")
    private Double price;
}

