package com.epam.alex.task4.entity;

/**
 * Created by AlexTuli on 11/26/15.
 *
 * @author Bocharnikov Alexandr
 */
public class Notification extends AbstractEntity{

    private String text;

    private User user;

    public Notification() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Notification that = (Notification) o;

        if (!text.equals(that.text)) return false;
        return user.equals(that.user);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + text.hashCode();
        result = 31 * result + user.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "\n " + text;
    }
}
