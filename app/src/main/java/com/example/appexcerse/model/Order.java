package com.example.appexcerse.model;

import java.util.Date;

public class Order {
    private String id;
    private String customerId;
    private Date createdDate;
    private Double totalAmount;
    private String Status;
    private Date DeliveredDate;

    public Order(String customerId, Date createdDate, Double totalAmount, String status, Date deliveredDate) {
        this.customerId = customerId;
        this.createdDate = createdDate;
        this.totalAmount = totalAmount;
        Status = status;
        DeliveredDate = deliveredDate;
    }

    public Order() {
    }

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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
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

    public Date getDeliveredDate() {
        return DeliveredDate;
    }

    public void setDeliveredDate(Date deliveredDate) {
        DeliveredDate = deliveredDate;
    }
}
