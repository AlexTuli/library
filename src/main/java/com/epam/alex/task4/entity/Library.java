package com.epam.alex.task4.entity;

/**
 * Class describes library.
 * Created by AlexTuli on 11/20/15.
 *
 * @author Bocharnikov Alexandr
 */
public class Library extends AbstractEntity {

    private String name;

    private String address;

    private String telephone;

    private String email;

    public Library() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Library library = (Library) o;

        if (!name.equals(library.name)) return false;
        if (!address.equals(library.address)) return false;
        if (!telephone.equals(library.telephone)) return false;
        return email.equals(library.email);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + telephone.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }
}
