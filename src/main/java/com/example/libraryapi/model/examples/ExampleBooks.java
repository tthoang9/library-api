package com.example.libraryapi.model.examples;

import com.example.libraryapi.model.Book;
import com.example.libraryapi.model.BookGenre;
import com.example.libraryapi.model.BookStatus;

import java.util.List;

public class ExampleBooks {

    public static Book HARRY_POTTER_PHILOSOPHERS_STONE = Book.builder()
            .isbn13("9780747532743")
            .title("Harry Potter and the Philosopher's Stone")
            .author("J. K. Rowling")
            .genres(List.of(BookGenre.FICTION, BookGenre.CHILDREN, BookGenre.FANTASY))
            .status(BookStatus.AVAILABLE)
            .build();

    public static Book HARRY_POTTER_HALF_BLOOD_PRINCE = Book.builder()
            .isbn13("9780439784542")
            .title("Harry Potter and the Half-Blood Prince")
            .author("J. K. Rowling")
            .genres(List.of(BookGenre.FICTION, BookGenre.CHILDREN, BookGenre.FANTASY))
            .status(BookStatus.MISSING)
            .build();

    public static Book TIDYING_UP = Book.builder()
            .isbn13("9781607747307")
            .title("The Life-Changing Magic of Tidying Up: The Japanese Art of Decluttering and Organizing")
            .author("Marie Kondo")
            .genres(List.of(BookGenre.NON_FICTION))
            .status(BookStatus.NOT_AVAILABLE)
            .build();
}
