package com.example.foodiemobileapp.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class OrderHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABSE_NAME = "ord.db";
    public static final String TAG = "";

    public OrderHelper(Context context) {
        super(context, DATABSE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_TABLE = "CREATE TABLE " + OrderContract.OrderEntry.TABLE_NAME + " ("
                + OrderContract.OrderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +  OrderContract.OrderEntry.COLUMN_NAME + " TEXT NOT NULL, "
                +  OrderContract.OrderEntry.COLUMN_QUANTITY + " TEXT NOT NULL, "
               // +  OrderContract.OrderEntry.COLUMN_PRICE + " TEXT NOT NULL, "
                +  OrderContract.OrderEntry.COLUMN_PRICE + " INTEGER NOT NULL, "
                +  OrderContract.OrderEntry.COLUMN_HASTOPPING + " TEXT NOT NULL, "
                +  OrderContract.OrderEntry.COLUMN_CREAM + " TEXT NOT NULL);";

                db.execSQL(SQL_TABLE);
    }

    public List readAllInfo(String req){
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                OrderContract.OrderEntry._ID,
                OrderContract.OrderEntry.COLUMN_NAME,
                OrderContract.OrderEntry.COLUMN_QUANTITY,
                OrderContract.OrderEntry.COLUMN_PRICE,
                OrderContract.OrderEntry.COLUMN_HASTOPPING,
                OrderContract.OrderEntry.COLUMN_CREAM
        };
        String sortOrder = OrderContract.OrderEntry.COLUMN_NAME+ " DESC";
        Cursor cursor = db.query(
                OrderContract.OrderEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
        List names = new ArrayList<>();
        List quantitties = new ArrayList<>();
        List prices = new ArrayList<>();
        List Hastoppings = new ArrayList<>();
        List creams = new ArrayList<>();


        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndexOrThrow(OrderContract.OrderEntry.COLUMN_NAME));
            String quantity = cursor.getString(cursor.getColumnIndexOrThrow(OrderContract.OrderEntry.COLUMN_QUANTITY));
            String price =  cursor.getString(cursor.getColumnIndexOrThrow( OrderContract.OrderEntry.COLUMN_PRICE));
            String hastoppinh =  cursor.getString(cursor.getColumnIndexOrThrow(OrderContract.OrderEntry.COLUMN_HASTOPPING));
            String cream =  cursor.getString(cursor.getColumnIndexOrThrow(OrderContract.OrderEntry.COLUMN_CREAM));

            names.add(name);
            quantitties.add(quantity);
            prices.add(price);
            Hastoppings.add(hastoppinh);
            creams.add(cream);
        }

        cursor.close();
        Log.i(TAG, "readAllInfo" + names);
        Log.i(TAG, "readAllInfo" + quantitties);
        Log.i(TAG, "readAllInfo" + prices);
        Log.i(TAG, "readAllInfo" + Hastoppings);
        Log.i(TAG, "readAllInfo" + creams);

        if(req == "name"){
            return names;
        }else if(req == "quantity"){
            return quantitties;
        }else if(req == "price"){
            return prices;
        }else if(req =="hastoppings"){
            return Hastoppings;
        }else if(req == "creams"){
            return creams;
        }
        else{
            return null;
        }

    }



    //tot
    public int sumPriceCartItems() {
        int result = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + OrderContract.OrderEntry.COLUMN_PRICE + ") from " + OrderContract.OrderEntry.TABLE_NAME, null);
        if (cursor.moveToFirst()) result = cursor.getInt(0);
        cursor.close();
        db.close();
        return result;
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
