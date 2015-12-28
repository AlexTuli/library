package com.epam.alex.task4.entity;

/**
 * Created by AlexTuli on 12/8/15.
 *
 * @author Bocharnikov Alexandr
 */
public class Role extends AbstractEntity {

    private static final int ID_ADMIN_ROLE = 3;
    private static final int ID_USER_ROLE = 2;
    private static final Role ADMIN_ROLE = new Role("ADMINISTRATOR", ID_ADMIN_ROLE);
    private static final Role USER_ROLE = new Role("USER", ID_USER_ROLE);
    private static final Role ANONYMOUS_ROLE = new Role("ANONYMOUS", 0);

    private String name;

    public Role() {

    }

    private Role(String role, int id) {
        super(id);
        this.name = role;
    }

    public static Role getAnonymousRole() {
        return ANONYMOUS_ROLE;
    }

    public static Role getAdminRole() {
        return ADMIN_ROLE;
    }

    public static Role getUserRole() {
        return USER_ROLE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Role role1 = (Role) o;

        return name.equals(role1.name);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Role - " + name;
    }
}
