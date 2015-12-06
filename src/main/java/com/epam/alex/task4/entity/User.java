package com.epam.alex.task4.entity;

/**
 * Class describes user of library. Have name of user, subscription of user and id of user.
 * <p>
 * Created by AlexTuli on 11/22/15.
 *
 * @author Bocharnikov Alexandr
 */
public class User extends AbstractEntity{

    private String name;

    private Subscription subscription;

    public User() {

    }

    public User(String name, int id) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (!name.equals(user.name)) return false;
        return subscription.equals(user.subscription);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + subscription.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Name of user: " + name;
    }
}
