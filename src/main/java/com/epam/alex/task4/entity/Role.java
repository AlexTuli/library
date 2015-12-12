package com.epam.alex.task4.entity;

/**
 * Created by AlexTuli on 12/8/15.
 *
 * @author Bocharnikov Alexandr
 */
public class Role extends AbstractEntity {

    int id;

    String role;

    public Role() {

    }

    public Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role1 = (Role) o;

        return role.equals(role1.role);

    }

    @Override
    public int hashCode() {
        return role.hashCode();
    }

    @Override
    public String toString() {
        return "Role - " + role;
    }
}
