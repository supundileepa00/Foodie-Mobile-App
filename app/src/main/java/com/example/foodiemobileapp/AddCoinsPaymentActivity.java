package com.example.foodiemobileapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodiemobileapp.Database.DBHelper;


public class AddCoinsPaymentActivity extends AppCompatActivity {

    String userID;
    int coins;
    String Amount;

    EditText fullName, cardNo, expMonth, expYear, cvv, NoOfCoins;
    SQLiteDatabase sqLiteDatabase;
    DBHelper dbHelper;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coins_payment);

        try{

            Intent intent2 = getIntent();
            userID = intent2.getStringExtra("userID");
            Amount = intent2.getStringExtra("PayAmount");

            fullName = findViewById(R.id.et_addCoinsfnamepb);
            cardNo = findViewById(R.id.et_addCoinsCardNopb);
            expMonth = findViewById(R.id.et_addCoinsCardExpMonthpb);
            expYear = findViewById(R.id.et_addCoinsCardExpYearpb);
            cvv = findViewById(R.id.et_addCoinsCVVpb);
            NoOfCoins = findViewById(R.id.et_addCoinsNumberpb);


        }catch (Exception e){

        }
    }

    public void addCoins(View view){

        Intent intent2 = getIntent();
        userID = intent2.getStringExtra("userID");
        Amount = intent2.getStringExtra("PayAmount");

        if(fullName.getText().toString().isEmpty() ||
                cardNo.getText().toString().isEmpty() ||
                expMonth.getText().toString().isEmpty() ||
                expYear.getText().toString().isEmpty() ||
                cvv.getText().toString().isEmpty() ||
                NoOfCoins.getText().toString().isEmpty()){

            //Alert

            //Alert

            AlertDialog alertDialog = new AlertDialog.Builder(this)
//set icon
                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                    .setTitle("Error")
//set message
                    .setMessage("Some fields are Empty!!")

//set negative button
                    .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .show();

        }
        else {
            if (Integer.parseInt(expMonth.getText().toString()) > 0 &&
                    Integer.parseInt(expMonth.getText().toString()) <= 12 &&
                    cvv.getText().toString().trim().length() == 3) {

                dbHelper = new DBHelper(this);
                sqLiteDatabase = dbHelper.getReadableDatabase();

                Cursor cursor = dbHelper.getNoofCoins(userID, sqLiteDatabase);
                if (cursor.moveToFirst()) {
                    coins = cursor.getInt(0);
                }

                //add current coins amount to existing amount
                int addCoinsAmount = Integer.parseInt(NoOfCoins.getText().toString());

                paymentCalculate calc = new paymentCalculate();


                int newCoinsAmount = calc.addCoins(coins, addCoinsAmount);

                int uid = Integer.parseInt(userID);

                int val = dbHelper.updateCoinsAmount(uid, newCoinsAmount);


                long val2 = dbHelper.addPaymentDetails(uid, fullName.getText().toString(),
                        cardNo.getText().toString(),
                        Integer.parseInt(expMonth.getText().toString()),
                        Integer.parseInt(expYear.getText().toString()),
                        Integer.parseInt(cvv.getText().toString()));
                if (val > 0 && val2 > 0) {

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.coinsound);
                    mediaPlayer.start();

                    Toast.makeText(this, "Coins Added!!", Toast.LENGTH_SHORT).show();


                    Intent in1 = new Intent(this, PaybyCoinsActivity.class);
                    in1.putExtra("userID", userID);
                    in1.putExtra("PayAmount", Amount);
                    startActivity(in1);
                }
            }
            else{
                //Validation Alert

                AlertDialog alertDialog = new AlertDialog.Builder(this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Payment Error")
//set message
                        .setMessage("Please enter details in valid format")

//set negative button
                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
            }
        }

    }




}