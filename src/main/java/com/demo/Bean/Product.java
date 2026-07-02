package com.demo.Bean;

import java.sql.Date;

public class Product {
    private int productId;
    private String productName;
    private String category;        
    private String storageType;     
    private double temperature;     
    private int quantity;
    private String unit;            
    private Date expiryDate;
    private Date receivedDate;
    private String supplierId;
    private String location;        
    private String status;         
    private double price;

    public Product() {}

    public Product(int productId, String productName, String category, String storageType,
                   double temperature, int quantity, String unit, Date expiryDate,
                   Date receivedDate, String supplierId, String location, String status, double price) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.storageType = storageType;
        this.temperature = temperature;
        this.quantity = quantity;
        this.unit = unit;
        this.expiryDate = expiryDate;
        this.receivedDate = receivedDate;
        this.supplierId = supplierId;
        this.location = location;
        this.status = status;
        this.price = price;
    }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getStorageType() { return storageType; }
    public void setStorageType(String storageType) { this.storageType = storageType; }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public Date getExpiryDate() { return expiryDate; }
    public void setExpiryDate(Date expiryDate) { this.expiryDate = expiryDate; }

    public Date getReceivedDate() { return receivedDate; }
    public void setReceivedDate(Date receivedDate) { this.receivedDate = receivedDate; }

    public String getSupplierId() { return supplierId; }
    public void setSupplierId(String supplierId) { this.supplierId = supplierId; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
