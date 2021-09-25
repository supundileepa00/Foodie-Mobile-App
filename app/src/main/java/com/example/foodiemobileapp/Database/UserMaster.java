package com.example.foodiemobileapp.Database;

import android.provider.BaseColumns;

public class UserMaster {

    private UserMaster(){}

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


}
