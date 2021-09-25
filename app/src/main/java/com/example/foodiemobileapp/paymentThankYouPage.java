package com.example.foodiemobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class paymentThankYouPage extends AppCompatActivity {
    String userID;
    String paymentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_thank_you_page);

        try {
            Intent intent = getIntent();
            paymentID = intent.getStringExtra("PayAmount");
            userID = intent.getStringExtra("userID");

            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void gotoHomePage(View view){
        Intent intent = new Intent(this, PaymentCheckout.class);
        intent.putExtra("userID", userID);
        startActivity(intent);
    }
}