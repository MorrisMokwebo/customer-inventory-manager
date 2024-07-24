package com.example.cim.model.constants;

public enum OrderStatus {
    OPEN("open"),
    PENDING("pending"),
    CANCELLED("cancelled");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
