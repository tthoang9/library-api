package com.example.libraryapi.controller;

import com.example.libraryapi.model.Book;
import com.example.libraryapi.model.BookGenre;
import com.example.libraryapi.model.BookStatus;
import com.example.libraryapi.service.LibraryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @PostMapping
    public ResponseEntity<Book> addBook(
            @RequestBody Book book
    ) {
        return new ResponseEntity<>(libraryService.addNewBook(book), HttpStatus.CREATED);
    }

    @DeleteMapping("/{isbn13}")
    public ResponseEntity<Void> deleteBook(
            @PathVariable String isbn13
    ) {
        boolean deleted = libraryService.removeBook(isbn13);
        return new ResponseEntity<>(
                deleted ?
                        HttpStatus.NO_CONTENT :
                        HttpStatus.NOT_FOUND
        );    }

    @GetMapping("/{isbn13}")
    public ResponseEntity<Book> getBook(
            @PathVariable String isbn13
    ) {
        Book book = libraryService.getBook(isbn13)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Book>> searchBooks(
            @RequestParam("title") Optional<String> title,
            @RequestParam("author") Optional<String> author,
            @RequestParam("genre") List<BookGenre> genre
    ) {
        return new ResponseEntity<>(libraryService.searchBooks(title, author, genre), HttpStatus.OK);
    }

    @PutMapping("{isbn13}")
    public ResponseEntity<Book> updateBookStatus(
            @PathVariable String isbn13,
            @RequestBody BookStatus newBookStatus
    ) {

        Book book = libraryService.updateBookStatus(isbn13, newBookStatus)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}
