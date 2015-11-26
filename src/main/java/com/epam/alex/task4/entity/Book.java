package com.epam.alex.task4.entity;

/**
 * Class describes book. Contains ID of the book and it's name
 * Created by AlexTuli on 11/20/15.
 *
 * @author Bocharnikov Alexandr
 */
public class Book {

    private int id;

    private String title;

    private String author;

    public Book() {

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        return !(author != null ? !author.equals(book.author) : book.author != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return  "ID = " + id +
                "\nTitle = " + title +
                "\nAuthor = " + author;
    }
}
