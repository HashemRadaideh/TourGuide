package com.TourGuide.model;

import java.util.ArrayList;
import java.util.List;

/**
 * User
 */
public class UserAccount {
    private String username;
    private String password;
    private String phoneNumber;
    private boolean status;
    private String firstName;
    private String lastName;
    private String city;
    private int age;
    private List<UserAccount> friends;

    public UserAccount(String username, String password, String phoneNumber, boolean status, String firstName,
            String lastName,
            String city,
            int age) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.age = age;
        this.friends = new ArrayList<>();
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean getStatus() {
        return status;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }

    public List<UserAccount> getFriends() {
        return friends;
    }

    public void addFriends(List<UserAccount> friends) {
        this.friends.addAll(friends);
    }
}
