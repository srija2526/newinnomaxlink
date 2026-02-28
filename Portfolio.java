package com.innomax.demo.entity;

import jakarta.persistence.*;

@Entity
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stockName;
    private int quantity;
    private double buyPrice;
    private double currentPrice;

    // No-argument constructor (required by JPA)
    public Portfolio() {}

    // Parameterized constructor
    public Portfolio(String stockName, int quantity, double buyPrice, double currentPrice) {
        this.stockName = stockName;
        this.quantity = quantity; 
        this.buyPrice = buyPrice;
        this.currentPrice = currentPrice;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public String getStockName() { return stockName; }
    public void setStockName(String stockName) { this.stockName = stockName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getBuyPrice() { return buyPrice; }
    public void setBuyPrice(double buyPrice) { this.buyPrice = buyPrice; }

    public double getCurrentPrice() { return currentPrice; }
    public void setCurrentPrice(double currentPrice) { this.currentPrice = currentPrice; }
}