package com.example.foodiemobileapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodiemobileapp.Database.DBHelper;


public class PaybyCoinsActivity extends AppCompatActivity {

    int coins;
    String amount;
    String userID;

    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    TextView totalPrice, AvailableCoins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payby_coins);

        try {


            AvailableCoins = findViewById(R.id.tv_availableCoins2);
            totalPrice = findViewById(R.id.tv_paymentTotalPrice2);

            Intent intent = getIntent();
            userID= intent.getStringExtra("userID");
            amount  = intent.getStringExtra("PayAmount");


            dbHelper = new DBHelper(getApplicationContext());
            sqLiteDatabase = dbHelper.getReadableDatabase();


            Cursor cursor = dbHelper.getNoofCoins(userID, sqLiteDatabase);
            if (cursor.moveToFirst()) {
                coins = cursor.getInt(0);
            }

            totalPrice.setText(amount);
            AvailableCoins.setText(String.valueOf(coins));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        try {


            AvailableCoins = findViewById(R.id.tv_availableCoins2);
            totalPrice = findViewById(R.id.tv_paymentTotalPrice2);

            Intent intent = getIntent();
            userID= intent.getStringExtra("userID");
            amount  = intent.getStringExtra("PayAmount");


            dbHelper = new DBHelper(getApplicationContext());
            sqLiteDatabase = dbHelper.getReadableDatabase();


            Cursor cursor = dbHelper.getNoofCoins(userID, sqLiteDatabase);
            if (cursor.moveToFirst()) {
                coins = cursor.getInt(0);
            }

            totalPrice.setText(amount);
            AvailableCoins.setText(String.valueOf(coins));
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void addCoins(View view){
        Intent in1 = getIntent();
        userID = in1.getStringExtra("userID");
        amount = in1.getStringExtra("PayAmount");

        Intent intent2 = new Intent(this, AddCoinsPaymentActivity.class);
        intent2.putExtra("userID", userID);
        intent2.putExtra("PayAmount", amount);

        startActivity(intent2);
    }

    public void pay(View view){
        try {


            Intent in1 = getIntent();
            userID = in1.getStringExtra("userID");
            amount = in1.getStringExtra("PayAmount");

            dbHelper = new DBHelper(getApplicationContext());
            sqLiteDatabase = dbHelper.getReadableDatabase();


            Cursor cursor = dbHelper.getNoofCoins(userID, sqLiteDatabase);
            if (cursor.moveToFirst()) {
                coins = cursor.getInt(0);
            }

            paymentCalculate calc = new paymentCalculate();
            int val = calc.payByCoins(coins, Integer.parseInt(amount));


            if (val == -99) {
                AlertDialog alertDialog = new AlertDialog.Builder(this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Payment Error")
//set message
                        .setMessage("Your Coins balance in insufficient.\n\nPlease add more Coins. ")
//set positive button

//set negative button
                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //set what should happen when negative button is clicked

                            }
                        })
                        .show();
            } else {
                dbHelper = new DBHelper(this);
                sqLiteDatabase = dbHelper.getReadableDatabase();

                int val2 = dbHelper.updateCoinsAmount(Integer.parseInt(userID), val);


                Intent intentPay = new Intent(this, paymentThankYouPage.class);
                intentPay.putExtra("userID", userID);
                startActivity(intentPay);

                Toast.makeText(this, "Payment Successful.", Toast.LENGTH_LONG).show();

            }
        }catch (Exception e){

        }

    }




}