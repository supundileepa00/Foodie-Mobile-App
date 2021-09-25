package com.example.foodiemobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodiemobileapp.Database.DBHelper;


public class PaymentCheckout extends AppCompatActivity {

    EditText amount;
    int UserID;
    String userid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_checkout);

        amount = findViewById(R.id.et_paymentAmount);

        DBHelper dbHelper = new DBHelper(this);

        Intent intent1 = getIntent();
        userid = intent1.getStringExtra("userID");


        //adding users and deleting payment entries
        //dbHelper.addUser("Dileepa","Dileepa");

        //dbHelper.deletePaymentDetails(30);
    }

    public void proceedCheckout(View view){

        Intent intent = new Intent(this, PaymentSelectActivity.class);

        //String uid= String.valueOf(UserID);
        String amnt = amount.getText().toString();

        intent.putExtra("PayAmount", amnt);
        intent.putExtra("userID", userid);

        startActivity(intent);

        Toast.makeText(this, "Go to Payment", Toast.LENGTH_SHORT).show();
    }
}