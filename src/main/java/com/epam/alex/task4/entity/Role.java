package com.epam.alex.task4.entity;

/**
 * Created by AlexTuli on 12/8/15.
 *
 * @author Bocharnikov Alexandr
 */
public class Role {

    String role;

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
