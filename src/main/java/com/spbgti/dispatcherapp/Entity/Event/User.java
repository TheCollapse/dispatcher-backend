package com.spbgti.dispatcherapp.Entity.Event;

import java.util.List;

public class User {
    private String login;
    private String password;
    private List<Permission> permissions;

    public User() {
    }

    public User(String login, String password, List<Permission> permissions) {
        this.login = login;
        this.password = password;
        this.permissions = permissions;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }
}

