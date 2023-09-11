package com.example.libraryapi.service;

import com.example.libraryapi.model.Book;
import com.example.libraryapi.model.BookGenre;
import com.example.libraryapi.model.BookStatus;
import com.example.libraryapi.model.examples.ExampleBooks;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    // LIBRARY SERVICES
    // adding books to the library,
    // checking availability,
    // denoting the book as checked out,
    // searching by title or author or genre,
    // remove book from the library

    private ConcurrentHashMap<String, Book> isbn13ToBook = new ConcurrentHashMap<>();

    public LibraryService() {
        addNewBook(ExampleBooks.HARRY_POTTER_PHILOSOPHERS_STONE);
        addNewBook(ExampleBooks.HARRY_POTTER_HALF_BLOOD_PRINCE);
        addNewBook(ExampleBooks.TIDYING_UP);
    }

    public Book addNewBook(Book book) {
        isbn13ToBook.put(book.getIsbn13(), book);
        return book;
    }

    public Optional<Book> getBook(String isbn13) {
        return Optional.ofNullable(isbn13ToBook.get(isbn13));
    }

    public Optional<Book> updateBookStatus(String isbn13, BookStatus newStatus) {
        Book book = isbn13ToBook.get(isbn13);
        if (book == null) {
            return Optional.empty();
        }
        isbn13ToBook.put(book.getIsbn13(), book.withStatus(newStatus));
        return Optional.of(book.withStatus(newStatus));
    }

    public boolean removeBook(String isbn13) {
        return isbn13ToBook.remove(isbn13) != null;
    }

    public List<Book> searchBooks(Optional<String> title, Optional<String> author, List<BookGenre> genres) {
        return isbn13ToBook.values().stream()
                .filter(book -> title.isEmpty() || book.getTitle().equals(title.get()))
                .filter(book -> author.isEmpty() || book.getAuthor().equals(author.get()))
                .filter(book -> genres.size() == 0 || new HashSet<>(book.getGenres()).containsAll(genres))
                .collect(Collectors.toList());
    }
}
