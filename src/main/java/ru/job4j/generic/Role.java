package ru.job4j.generic;

public class Role extends Base {

    private final String userRole;

    public Role(String id, String role) {
        super(id);
        this.userRole = role;
    }

    public String getUserRole() {
        return userRole;
    }
}
