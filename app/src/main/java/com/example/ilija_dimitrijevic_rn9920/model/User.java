package com.example.ilija_dimitrijevic_rn9920.model;

public class User {

    private String username;
    private String password;
    private String email;
    private UserType userType;

    public User(UserType userType,String email,String username,String password) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public UserType getUserType() {
        return userType;
    }
}
