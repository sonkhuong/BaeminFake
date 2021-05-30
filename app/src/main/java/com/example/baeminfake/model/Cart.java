package com.example.baeminfake.model;

public class Cart {

    private int id;
    private int payment;
    private int soluong;
    private String name;
    private double price;
    private String restaurant;
    private int rate;
    private int orders;

    public Cart() {
    }

    public Cart(int id, int payment, int soluong, String name, double price, String restaurant, int rate, int orders) {
        this.id = id;
        this.payment = payment;
        this.soluong = soluong;
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
        this.rate = rate;
        this.orders = orders;
    }

    public Cart(int payment, int soluong, String name, double price, String restaurant, int rate, int orders) {
        this.payment = payment;
        this.soluong = soluong;
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

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
