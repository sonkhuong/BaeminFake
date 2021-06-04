package com.example.baeminfake.model;

public class User {

    private int id;
    private String img_resource;
    private String name;
    private String email;
    private String phone;

    public User() {
    }

    public User(int id, String img_resource, String name, String email, String phone) {
        this.id = id;
        this.img_resource = img_resource;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public User(String img_resource, String name, String email, String phone) {
        this.img_resource = img_resource;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public User(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg_resource() {
        return img_resource;
    }

    public void setImg_resource(String img_resource) {
        this.img_resource = img_resource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
