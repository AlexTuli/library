package com.epam.alex.task4.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * This is subscription for users of library. This class contains name of user and
 * book list (books that user take from library)
 * Created by AlexTuli on 11/22/15.
 *
 * @author Bocharnikov Alexandr
 */
public class Subscription {

    private String nameOfUser;

    private List<Book> bookList;

    public Subscription() {
        bookList = new ArrayList<>();
    }

    public Subscription(String nameOfUser) {
        this.nameOfUser = nameOfUser;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public Book getBook(int index) {
        return bookList.get(index);
    }

    public void addBook(Book book) {
        bookList.add(book);
    }

    public String getNameOfUser() {
        return nameOfUser;
    }

    public void setNameOfUser(String nameOfUser) {
        this.nameOfUser = nameOfUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscription that = (Subscription) o;

        if (!nameOfUser.equals(that.nameOfUser)) return false;
        return !(bookList != null ? !bookList.equals(that.bookList) : that.bookList != null);

    }

    @Override
    public int hashCode() {
        int result = nameOfUser.hashCode();
        result = 31 * result + (bookList != null ? bookList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "This is subscription of user: " + nameOfUser +
                "\nHe take " + bookList.size() + " books from library";
    }
}
