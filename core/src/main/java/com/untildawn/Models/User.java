package com.untildawn.Models;

public class User {
    private String username;
    private String email;
    private String password;

    public User(String username,
                String email,
                String password) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
