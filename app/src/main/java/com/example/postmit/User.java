package com.example.postmit;

public class User {
    private int coffeeCount;
    private int giftCoffeeCount;
    private String id;
    private String password;
    private String userEmail;
    private String userName;

    public User(String id, String password, String userEmail, String userName) {
        this.coffeeCount = 0;
        this.giftCoffeeCount = 0;
        this.id = id;
        this.password = password;
        this.userEmail = userEmail;
        this.userName = userName;
    }

    public User(){

    }

    public int getCoffeeCount() {
        return coffeeCount;
    }

    public void setCoffeeCount(int coffeeCount) {
        this.coffeeCount = coffeeCount;
    }

    public int getGiftCoffeeCount() {
        return giftCoffeeCount;
    }

    public void setGiftCoffeeCount(int giftCoffeeCount) {
        this.giftCoffeeCount = giftCoffeeCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
