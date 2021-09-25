package com.example.foodiemobileapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "FoodieDB";
    private static final String TAG = "";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_UserTable = "CREATE TABLE " + UserMaster.Users.TABLE_NAME + "(" +
                UserMaster.Users.COLUMN_NAME_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                UserMaster.Users.COLUMN_NAME_USERNAME + " TEXT," +
                UserMaster.Users.COLUMN_NAME_PASSWORD + " TEXT)";


        String SQL_CREATE_CoinsTable = "CREATE TABLE " + UserMaster.Coins.TABLE_NAME + "(" +
                UserMaster.Coins.COLUMN_NAME_USERID + " INTEGER PRIMARY KEY REFERENCES "+ UserMaster.Users.TABLE_NAME + "(" +UserMaster.Users.COLUMN_NAME_USERID +")," +
                UserMaster.Coins.COLUMN_NAME_Coins + " INTEGER)";



        String SQL_CREATE_PaymentTable = "CREATE TABLE " + UserMaster.Payment.TABLE_NAME + "(" +
                UserMaster.Payment.COLUMN_NAME_PAYMENTID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                UserMaster.Payment.COLUMN_NAME_UserID + " INTEGER REFERENCES " + UserMaster.Users.TABLE_NAME + "(" +UserMaster.Users.COLUMN_NAME_USERID +")," +
                UserMaster.Payment.COLUMN_NAME_FULLNAME + " TEXT," +
                UserMaster.Payment.COLUMN_NAME_CARDNO + " TEXT," +
                UserMaster.Payment.COLUMN_NAME_ExpireMonth + " INTEGER," +
                UserMaster.Payment.COLUMN_NAME_ExpireYear + " INTEGER," +
                UserMaster.Payment.COLUMN_NAME_CVV + " INTEGER)";

        db.execSQL(SQL_CREATE_UserTable);
        db.execSQL(SQL_CREATE_CoinsTable);
        db.execSQL(SQL_CREATE_PaymentTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Payment

    public void addUser(String username, String password){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.Users.COLUMN_NAME_USERNAME, username);
        values.put(UserMaster.Users.COLUMN_NAME_PASSWORD, password);

        db.insert(UserMaster.Users.TABLE_NAME, null, values);

    }

    public long addPaymentDetails(int userID, String fullName, String CardNo, int ExpMonth, int ExpYear, int cvv){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.Payment.COLUMN_NAME_UserID, userID);
        values.put(UserMaster.Payment.COLUMN_NAME_FULLNAME, fullName);
        values.put(UserMaster.Payment.COLUMN_NAME_CARDNO, CardNo);
        values.put(UserMaster.Payment.COLUMN_NAME_ExpireMonth, ExpMonth);
        values.put(UserMaster.Payment.COLUMN_NAME_ExpireYear, ExpYear);
        values.put(UserMaster.Payment.COLUMN_NAME_CVV, cvv);

        long rows = db.insert(UserMaster.Payment.TABLE_NAME, null, values);
        return rows;

    }



    public void deletePaymentDetails(int paymentID){
        SQLiteDatabase db = getReadableDatabase();

        String selection = UserMaster.Payment.COLUMN_NAME_PAYMENTID + " LIKE ?";

        String[] selectionArgs = { Integer.toString(paymentID)};

        db.delete(UserMaster.Payment.TABLE_NAME, selection, selectionArgs);

    }

    public int updatePaymentDetails(int paymentID, String fullName, String CardNo, int ExpMonth, int ExpYear, int cvv){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.Payment.COLUMN_NAME_FULLNAME, fullName);
        values.put(UserMaster.Payment.COLUMN_NAME_CARDNO, CardNo);
        values.put(UserMaster.Payment.COLUMN_NAME_ExpireMonth,ExpMonth);
        values.put(UserMaster.Payment.COLUMN_NAME_ExpireYear,ExpYear);
        values.put(UserMaster.Payment.COLUMN_NAME_CVV,cvv);

        String selection = UserMaster.Payment.COLUMN_NAME_PAYMENTID + " LIKE ?";
        String[] selectionArgs = {Integer.toString(paymentID)};

        int count = db.update(
                UserMaster.Payment.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        return count;

    }


    public Cursor getPaymentID(String fullName, SQLiteDatabase sqLiteDatabase){
        String[] projections = {UserMaster.Payment.COLUMN_NAME_PAYMENTID};
        String selection = UserMaster.Payment.COLUMN_NAME_FULLNAME+ " LIKE ?";
        String[] selectionArgs = {fullName};

        Cursor cursor = sqLiteDatabase.query(UserMaster.Payment.TABLE_NAME,projections,selection,selectionArgs,null,null,null);
        return cursor;
    }



    //payment ID is get As String
    public Cursor getPaymentDetails(String paymentID, SQLiteDatabase sqLiteDatabase){
        String[] projections = {UserMaster.Payment.COLUMN_NAME_FULLNAME, UserMaster.Payment.COLUMN_NAME_CARDNO, UserMaster.Payment.COLUMN_NAME_ExpireMonth, UserMaster.Payment.COLUMN_NAME_ExpireYear, UserMaster.Payment.COLUMN_NAME_CVV};
        String selection = UserMaster.Payment.COLUMN_NAME_PAYMENTID+ " LIKE ?";
        String[] selectionArgs = {paymentID};

        Cursor cursor = sqLiteDatabase.query(UserMaster.Payment.TABLE_NAME, projections,selection,selectionArgs, null,null,null);
        return cursor;
    }

    //get user details
    //fro userlogin
    public Cursor getUser(String username, SQLiteDatabase sqLiteDatabase){
        String[] projections = {UserMaster.Users.COLUMN_NAME_PASSWORD, UserMaster.Users.COLUMN_NAME_USERID};
        String selection = UserMaster.Users.COLUMN_NAME_USERNAME+ " LIKE ?";
        String[] selectionArgs = {username};
        Cursor cursor = sqLiteDatabase.query(UserMaster.Users.TABLE_NAME, projections,selection,selectionArgs,null,null,null);
        return cursor;
    }


    //Coins

    public long addCoins(int userID, int coins){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.Coins.COLUMN_NAME_USERID, userID);
        values.put(UserMaster.Coins.COLUMN_NAME_Coins, coins);

        long rows = db.insert(UserMaster.Coins.TABLE_NAME, null, values);
        return rows;
    }

    //get coins amount
    public Cursor getNoofCoins(String userID, SQLiteDatabase sqLiteDatabase){
        String[] projections = {UserMaster.Coins.COLUMN_NAME_Coins};
        String selection = UserMaster.Coins.COLUMN_NAME_USERID+ " LIKE ?";
        String[] selectionArgs = {userID};

        Cursor cursor = sqLiteDatabase.query(UserMaster.Coins.TABLE_NAME, projections,selection, selectionArgs, null,null,null);
        return cursor;
    }

    //update coins amount
    public int updateCoinsAmount(int userID, int coins){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(UserMaster.Coins.COLUMN_NAME_Coins, coins);

        String selection = UserMaster.Coins.COLUMN_NAME_USERID + " LIKE ?";
        String[] selectionArgs = {Integer.toString(userID)};


        int count = db.update(
                UserMaster.Coins.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        return count;

    }
}