package com.epam.alex.task4.entity;

/**
 * Class describes user of library. Have name of user, subscription of user and id of user. *
 * Created by AlexTuli on 11/22/15.
 *
 * @author Bocharnikov Alexandr
 */
public class User extends AbstractEntity {

    private String firstName;

    private String lastName;

    private String login;

    private String password;

    private Subscription subscription;

    private Role role;

    public User() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        return login.equals(user.login) && subscription.equals(user.subscription);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + subscription.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Login of user: " + login;
    }
}
