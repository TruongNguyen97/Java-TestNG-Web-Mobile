package com.automation.framework.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {
    private String email;
    private String password;
    private String mailslurpInboxId;
    private String currentBalance;



    

    // Constructor mặc định (no-argument constructor)
    public Account() {
    }

    @JsonCreator
    public Account(@JsonProperty("email") String email,
                   @JsonProperty("password") String password,
                   @JsonProperty("mailslurpInboxId") String mailslurpInboxId,
                   @JsonProperty("currentBalance") String currentBalance) {
        this.email = email;
        this.password = password;
        this.mailslurpInboxId = mailslurpInboxId;
        this.currentBalance = currentBalance;
    }

    // Getters and setters
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

    public String getMailslurpInboxId() {
        return mailslurpInboxId;
    }

    public void setMailslurpInboxId(String mailslurpInboxId) {
        this.mailslurpInboxId = mailslurpInboxId;
    }

    public String getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }

    @Override
    public String toString() {
        return "Account{email='" + email + "', password='" + password + "'}";
    }
}