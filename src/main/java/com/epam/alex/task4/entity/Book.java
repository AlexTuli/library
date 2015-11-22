package com.epam.alex.task4.entity;

/**
 * Class describes book. Contains ID of the book and it's name
 * Created by AlexTuli on 11/20/15.
 *
 * @author Bocharnikov Alexandr
 */
public class Book {

    private String id;

    private String nameOfBook;

    public Book() {

    }

    public Book(String nameOfBook) {
        this.nameOfBook = nameOfBook;
    }

    public String getNameOfBook() {
        return nameOfBook;
    }

    public void setNameOfBook(String nameOfBook) {
        this.nameOfBook = nameOfBook;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != null ? !id.equals(book.id) : book.id != null) return false;
        return nameOfBook.equals(book.nameOfBook);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + nameOfBook.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Name of book: " + nameOfBook +
                "ID: " + id;
    }
}
