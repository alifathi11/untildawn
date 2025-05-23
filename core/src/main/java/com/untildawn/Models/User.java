package com.untildawn.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String username;
    private String email;
    private String password;
    private SecurityQuestion securityQuestion;
    private GameProfile gameProfile;
    private InputPreferences inputPreferences;

    public User() {}

    public User(String username,
                String email,
                String password,
                SecurityQuestion securityQuestion) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.securityQuestion = securityQuestion;
        this.gameProfile = new GameProfile(0, 0, 0);
        this.inputPreferences = new InputPreferences();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public SecurityQuestion getSecurityQuestion() {
        return securityQuestion;
    }

    public GameProfile getGameProfile() {
        return gameProfile;
    }

    public InputPreferences getInputPreferences() {
        return inputPreferences;
    }
}
