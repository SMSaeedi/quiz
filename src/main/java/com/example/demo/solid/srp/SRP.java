package com.example.demo.solid.srp;

import lombok.Getter;

public class SRP {
    public static void main(String[] args) {
        User user = new User("john_doe", "password123");

        UserValidator validator = new UserValidator();
        if (validator.validate(user)) {
            UserRepository repository = new UserRepository();
            repository.saveToDatabase(user);

            UserJsonConverter jsonConverter = new UserJsonConverter();
            String userJson = jsonConverter.toJson(user);
            System.out.println(userJson);
        } else {
            System.out.println("User validation failed.");
        }
    }
}

class UserJsonConverter {
    public String toJson(User user) {
        return "{ \"username\": \"" + user.getUsername() + "\", \"password\": \"" + user.getPassword() + "\" }";
    }
}

class UserRepository {
    public void saveToDatabase(User user) {
        // Code to save user to the database
    }
}

class UserValidator {
    public boolean validate(User user) {
        return user.getUsername() != null && !user.getUsername().isEmpty() &&
                user.getPassword() != null && !user.getPassword().isEmpty();
    }
}

@Getter
class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

}