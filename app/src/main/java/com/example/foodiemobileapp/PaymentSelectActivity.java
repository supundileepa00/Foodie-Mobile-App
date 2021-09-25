package com.example.foodiemobileapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodiemobileapp.Database.DBHelper;


public class PaymentSelectActivity extends AppCompatActivity {
    int coins;
    String userID;
    String amount;


    TextView NoOfcoins;
    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_select);

        try {

            dbHelper = new DBHelper(getApplicationContext());
            sqLiteDatabase = dbHelper.getReadableDatabase();

            Intent intent = getIntent();
            amount = intent.getStringExtra("PayAmount");
            userID = intent.getStringExtra("userID");

            System.out.println(userID+ "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

            dbHelper = new DBHelper(getApplicationContext());

            //creating entry in coins table for user
            int uid = Integer.parseInt(userID);
            int checkCoins = -1;
            Cursor cursor1 = dbHelper.getNoofCoins(String.valueOf(userID),sqLiteDatabase);
            if(cursor1.moveToFirst()){
                checkCoins = cursor1.getInt(0);
            }
            if(checkCoins < 0){
                long val = dbHelper.addCoins(uid, 0);
            }



            Cursor cursor = dbHelper.getNoofCoins(String.valueOf(userID),sqLiteDatabase);
            if(cursor.moveToFirst()){
                coins = cursor.getInt(0);
            }
            System.out.println("Coins :" +coins+ " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            NoOfcoins = findViewById(R.id.tv_selectPayment2);
            NoOfcoins.setText(String.valueOf(coins));

        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void payByCoins(View view){
        Intent intent = new Intent(this, PaybyCoinsActivity.class);
        intent.putExtra("userID",userID);
        intent.putExtra("PayAmount", amount);
        startActivity(intent);

    }

    public void payByCard(View view){
        Intent intent = new Intent(this, PayByCardActivity.class);
        intent.putExtra("userID", userID);
        intent.putExtra("PayAmount", amount);
        startActivity(intent);
    }






}