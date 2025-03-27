package com.automation.framework;

import com.automation.framework.core.api.APIClient;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        APIClient client = new APIClient("http://localhost:8080");

        System.out.println(client.sendGetRequest("/api/v1/users"));

    }
}