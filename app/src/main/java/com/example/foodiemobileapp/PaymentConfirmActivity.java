package com.example.foodiemobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentConfirmActivity extends AppCompatActivity {

    String paymentID;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_confirm);

        Intent in = getIntent();
        paymentID = in.getStringExtra("PaymentID");
        userID = in.getStringExtra("userID");
    }

    public void navigateToEdit(View view){
        Intent intent1 = getIntent();

        paymentID= intent1.getStringExtra("PaymentID");

        Intent intent = new Intent(this, EditPaymentDetails.class);

        intent.putExtra("PaymentID", paymentID);
        intent.putExtra("userID",userID);

        startActivity(intent);
    }

    public void confirmPayment(View view){
        Intent intent1 = getIntent();
        userID= intent1.getStringExtra("userID");

        Intent intent2 = new Intent(this, paymentThankYouPage.class);
        intent2.putExtra("PaymentID", paymentID);
        intent2.putExtra("userID",userID);
        startActivity(intent2);
    }


}