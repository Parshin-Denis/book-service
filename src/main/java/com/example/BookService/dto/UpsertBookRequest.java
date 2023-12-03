package com.example.BookService.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpsertBookRequest {
    @NotBlank(message = "Необходимо ввести имя автора")
    private String author;

    @NotBlank(message = "Необходимо ввести название книги")
    private String title;

    @NotBlank(message = "Необходимо ввести имя категории")
    private String category;
}
