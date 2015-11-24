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

    private User user;

    private List<Book> bookList;

    public Subscription() {
        bookList = new ArrayList<>();
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public Book getBook(int index) {
        return bookList.get(index);
    }

    public void removeBook(int index) {
        bookList.remove(index);
    }

    public void addBook(Book book) {
        bookList.add(book);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscription that = (Subscription) o;

        if (!user.equals(that.user)) return false;
        return bookList.equals(that.bookList);

    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + bookList.hashCode();
        return result;
    }
}
