package com.epam.alex.task4.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexTuli on 11/20/15.
 *
 * @author Bocharnikov Alexandr
 */
public class Library {

    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Library library = (Library) o;

        return !(books != null ? !books.equals(library.books) : library.books != null);

    }

    @Override
    public int hashCode() {
        return books != null ? books.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Size of Library is:  " + books.size();
    }
}
