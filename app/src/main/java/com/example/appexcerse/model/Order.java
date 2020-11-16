package com.example.appexcerse.model;

import java.util.List;

public class Order {
    private String id;
    private String customerId;
    private String createdDate;
    private Double totalAmount;
    private String Status;
    private String DeliveredDate;
    private List<Product> items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDeliveredDate() {
        return DeliveredDate;
    }

    public void setDeliveredDate(String deliveredDate) {
        DeliveredDate = deliveredDate;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    public Order() {
    }

    public Order(String customerId, String createdDate, Double totalAmount, String status, String deliveredDate, List<Product> items) {
        this.customerId = customerId;
        this.createdDate = createdDate;
        this.totalAmount = totalAmount;
        Status = status;
        DeliveredDate = deliveredDate;
        this.items = items;
    }
}
