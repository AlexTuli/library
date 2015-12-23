package com.epam.alex.task4.entity;

import com.epam.alex.task4.propmanager.FullPropertyReader;
import com.epam.alex.task4.propmanager.PropertyReader;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AlexTuli on 12/22/15.
 *
 * @author Bocharnikov Alexandr
 */
public class RoleFactory {

    private static final RoleFactory INSTANCE = new RoleFactory();

    /**
     * Contains cached Roles
     */
    private Map<String, Role> roleMap;

    /**
     * Read ID of role from property
     */
    PropertyReader propertyReader;



    private RoleFactory() {
        roleMap = new HashMap<>();
        propertyReader = new FullPropertyReader();
        Map<String, String> stringStringMap = propertyReader.readProperty("library.properties");
        int idAdmin = Integer.parseInt(stringStringMap.get("role.admin.id"));
        int idUser  = Integer.parseInt(stringStringMap.get("role.user.id"));
        roleMap.put("admin", new Role(Role.ADMIN, idAdmin));
        roleMap.put("user", new Role(Role.USER, idUser));
    }

    public static RoleFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Return Admin role
     * @return Admin role
     */
    public Role getAdminRole() {
        return roleMap.get("admin");
    }

    /**
     * Return role of User
     * @return User role
     */
    public Role getUserRole() {
        return roleMap.get("user");
    }

}
