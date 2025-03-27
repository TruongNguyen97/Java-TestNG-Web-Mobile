package com.automation.framework.core.api;

public class APIClient {
    // Base URL for API requests
    private String baseUrl;

    // Constructor
    public APIClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    // Method to send GET requests
    public String sendGetRequest(String endpoint) {
        // Implementation for sending GET request
        return "Response from GET " + baseUrl + endpoint;
    }

    // Method to send POST requests
    public String sendPostRequest(String endpoint, String payload) {
        // Implementation for sending POST request
        return "Response from POST " + baseUrl + endpoint;
    }

    // Getter for baseUrl
    public String getBaseUrl() {
        return baseUrl;
    }

    // Setter for baseUrl
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
