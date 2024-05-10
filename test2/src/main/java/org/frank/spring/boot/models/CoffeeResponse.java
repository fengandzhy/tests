package org.frank.spring.boot.models;

public class CoffeeResponse {
    private String message;
    private String prepared;

    public CoffeeResponse(String message, String prepared) {
        this.message = message;
        this.prepared = prepared;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPrepared() {
        return prepared;
    }

    public void setPrepared(String prepared) {
        this.prepared = prepared;
    }
}
