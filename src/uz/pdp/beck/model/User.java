package uz.pdp.beck.model;

import java.util.UUID;

public class User {
    private final UUID id = UUID.randomUUID();
    private String name;
    private String username; // unique
    private String phoneNumber;
    private String password;

    public User() {
    }

    public User(String name, String username, String phoneNumber, String password) {
        this.name = name;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
