package com.epam.alex.task4.entity;

/**
 * Class describes book. Contains name of the book and the author
 * Created by AlexTuli on 11/20/15.
 *
 * @author Bocharnikov Alexandr
 */
public class Book extends AbstractEntity {

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


    @Override
    public String toString() {
        return "ID = " + super.getId() +
                "\nTitle = " + title +
                "\nAuthor = " + author;
    }
}
