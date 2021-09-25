package com.example.foodiemobileapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodiemobileapp.Database.DBHelper;


public class PayByCardActivity extends AppCompatActivity {

    EditText fullName, cardNo, expMonth, expYear, cvv;
    TextView TotAmount;
    String fname;
    SQLiteDatabase sqliteDB;
    DBHelper userDBHelper;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_by_card);



            fullName = findViewById(R.id.et_payByCardFullName);
            cardNo = findViewById(R.id.et_payByCardCardNo);
            expMonth = findViewById(R.id.et_payByCardExpMonth);
            expYear = findViewById(R.id.et_payByCardExpYear);
            cvv = findViewById(R.id.et_payByCardCVV);
            TotAmount = findViewById(R.id.tv_payByCardAmount);

            Intent intent = getIntent();
            userID = intent.getStringExtra("userID");
            String payAmount = intent.getStringExtra("PayAmount");


            TotAmount.setText(payAmount);

            DBHelper dbHelper = new DBHelper(this);


            //adding users and deleting payment entries
            //dbHelper.addUser("kamal","kamal");

            //dbHelper.deletePaymentDetails(22);

    }
    public void addPayment(View view){


            if(fullName.getText().toString().isEmpty() ||
                    cardNo.getText().toString().isEmpty() ||
                    expMonth.getText().toString().isEmpty() ||
                    expYear.getText().toString().isEmpty() ||
                    cvv.getText().toString().isEmpty()){
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

            else{
                if( Integer.parseInt(expMonth.getText().toString()) > 0 &&
                        Integer.parseInt(expMonth.getText().toString()) <=12 &&
                        cvv.getText().toString().trim().length() == 3){

                    int uid = Integer.parseInt(userID);



                    DBHelper dbHelper = new DBHelper(this);
                    long val =dbHelper.addPaymentDetails(uid, fullName.getText().toString(),
                            cardNo.getText().toString(),
                            Integer.parseInt(expMonth.getText().toString()),
                            Integer.parseInt(expYear.getText().toString()),
                            Integer.parseInt(cvv.getText().toString()));


                    if(val > 0){
                        Toast.makeText(this, "Payment Details Successfully added", Toast.LENGTH_SHORT).show();


                        fname = fullName.getText().toString();
                        userDBHelper = new DBHelper(getApplicationContext());
                        sqliteDB = userDBHelper.getReadableDatabase();
                        Cursor cursor = dbHelper.getPaymentID(fname, sqliteDB);

                        int id = 0;
                        if(cursor.moveToFirst()){
                            id = cursor.getInt(0);
                        }
                        Intent intent = new Intent(this, PaymentConfirmActivity.class);

                        String pid = String.valueOf(id);
                        intent.putExtra("PaymentID", pid);
                        intent.putExtra("userID",userID);

                        startActivity(intent);



                    }
                    else{
                        Toast.makeText(this, "Payment details not added", Toast.LENGTH_SHORT).show();
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