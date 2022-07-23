package com.example.application;

public class User {

    private String id;
    private String email;
    private String user_type;

    public User() {
    }

    public User(String id, String email, String user_type) {
        this.id = id;
        this.email = email;
        this.user_type = user_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
}
