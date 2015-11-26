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

    private int ID;

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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscription that = (Subscription) o;

        if (ID != that.ID) return false;
        if (!user.equals(that.user)) return false;
        return !(bookList != null ? !bookList.equals(that.bookList) : that.bookList != null);

    }

    @Override
    public int hashCode() {
        int result = ID;
        result = 31 * result + user.hashCode();
        result = 31 * result + (bookList != null ? bookList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "user=" + user +
                ", bookList=" + bookList +
                '}';
    }
}
