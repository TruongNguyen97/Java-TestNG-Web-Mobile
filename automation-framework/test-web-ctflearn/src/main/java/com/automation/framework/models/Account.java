package com.automation.framework.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {
    private String username;
    private String password;

    // Constructor mặc định (no-argument constructor)
    public Account() {
    }

    @JsonCreator
    public Account(@JsonProperty("username") String username,
                   @JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{username='" + username + "', password='" + password + "'}";
    }
}