package com.example.demo.solid.srp.violation;

import static java.lang.System.out;

public class SRP {
    public static void main(String[] args) {
        User user = new User();
        user.setUsername("john_doe");
        user.setPassword("password123");

        if (user.validate()) {
            user.saveToDatabase();

            String userJson = user.toJson();
            out.println(userJson);
        } else {
            out.println("User validation failed.");
        }
    }
}

class User {
    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Responsibility 1: User validation
    public boolean validate() {
        return username != null && !username.isEmpty() && password != null && !password.isEmpty();
    }

    // Responsibility 2: User persistence
    public void saveToDatabase() {
        // Code to save user to the database
    }

    // Responsibility 3: User representation
    public String toJson() {
        return "{ \"username\": \"" + username + "\", \"password\": \"" + password + "\" }";
    }
}