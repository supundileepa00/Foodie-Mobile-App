package com.example.foodiemobileapp.Database;

import android.provider.BaseColumns;

public class UserMaster {

    private int id;
    private String Title,Description,Price,Category;

    public UserMaster(){}

    public static class Users implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_USERID = "userID";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }

    public static class Coins implements BaseColumns{
        public static final String TABLE_NAME = "coins";
        public static final String COLUMN_NAME_USERID = "userID";
        public static final String COLUMN_NAME_Coins = "coins";

    }

    public static class Payment implements BaseColumns{
        public static final String TABLE_NAME = "payment";
        public static final String COLUMN_NAME_PAYMENTID = "paymentID";
        public static final String COLUMN_NAME_UserID= "userID";
        public static final String COLUMN_NAME_FULLNAME = "fullName";
        public static final String COLUMN_NAME_CARDNO = "cardNo";
        public static final String COLUMN_NAME_ExpireMonth = "expMonth";
        public static final String COLUMN_NAME_ExpireYear = "expYear";
        public static final String COLUMN_NAME_CVV = "cvv";

    }
    public UserMaster(int id, String title, String description, String price, String category) {
        this.id = id;
        Title = title;
        Description = description;
        Price = price;
        Category = category;
    }

    public UserMaster(String title, String description, String price, String category) {
        Title = title;
        Description = description;
        Price = price;
        Category = category;
    }
    public UserMaster(int id, String description, String price) {
        this.id = id;
        Description = description;
        Price = price;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }


}
