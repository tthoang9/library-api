package com.example.libraryapi.model;

import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.util.List;

@Builder
@Data
@With
public class Book {
    private final String isbn13;
    private final String title;
    private final String author;
    private final List<BookGenre> genres;
    private final BookStatus status;
}
