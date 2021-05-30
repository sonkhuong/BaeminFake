package com.example.baeminfake.model;

public class Food {

    private int id;
    private String name;
    private double price;
    private String restaurant;
    private int rate;
    private int orders;

    public Food() {
    }

    public Food(int id, String name, double price, String restaurant, int rate, int orders) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
        this.rate = rate;
        this.orders = orders;
    }

    public Food(String name, double price, String restaurant, int rate, int orders) {
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
        this.rate = rate;
        this.orders = orders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }
}
