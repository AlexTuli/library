package com.epam.alex.task4.entity;

/**
 * Class describes user of library. Have name of user, subscription of user and id of user.
 * <p>
 * Created by AlexTuli on 11/22/15.
 *
 * @author Bocharnikov Alexandr
 */
public class User {

    private String name;

    private Subscription subscription;

    private String id;

    public User() {
        subscription = new Subscription(name);
    }

    public User(String name, String id) {
        this.name = name;
        subscription = new Subscription(name);
        this.id = id;
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

        User user = (User) o;

        if (!name.equals(user.name)) return false;
        if (subscription != null ? !subscription.equals(user.subscription) : user.subscription != null) return false;
        return !(id != null ? !id.equals(user.id) : user.id != null);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (subscription != null ? subscription.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Name of user: " + name;
    }
}
