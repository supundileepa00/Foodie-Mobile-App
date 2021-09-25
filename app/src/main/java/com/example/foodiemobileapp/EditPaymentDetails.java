package com.example.foodiemobileapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodiemobileapp.Database.DBHelper;


public class EditPaymentDetails extends AppCompatActivity {

    EditText fullName,cardNo,expMonth,expYear,cvv;

    DBHelper userDBHelper;
    SQLiteDatabase sqLiteDatabase;
    String paymentID;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_payment_details);


        fullName = (EditText)findViewById(R.id.et_payByCardFullName2);
        cardNo = (EditText)findViewById(R.id.et_payByCardCardNo3);
        expMonth = (EditText)findViewById(R.id.et_payByCardExpMonth2);
        expYear = (EditText)findViewById(R.id.et_payByCardExpYear2);
        cvv = (EditText)findViewById(R.id.et_payByCardCVV2);

        Intent intent = getIntent();

        paymentID = intent.getStringExtra("PaymentID");
        userID = intent.getStringExtra("userID");

        userDBHelper = new DBHelper(getApplicationContext());
        sqLiteDatabase = userDBHelper.getReadableDatabase();
        Cursor cursor = userDBHelper.getPaymentDetails(paymentID, sqLiteDatabase);




        String DBfname = null;
        String DBcardNo = null;
        int DBexpMonth = 0;
        int DBexpYear = 0;
        int DBcvv = 0;

        if(cursor.moveToFirst()){
            DBfname = cursor.getString(0);
            DBcardNo = cursor.getString(1);
            DBexpMonth = cursor.getInt(2);
            DBexpYear = cursor.getInt(3);
            DBcvv = cursor.getInt(4);

        }

        fullName.setText(DBfname);
        cardNo.setText(DBcardNo);
        expMonth.setText(String.valueOf(DBexpMonth));
        expYear.setText(String.valueOf(DBexpYear));
        cvv.setText(String.valueOf(DBcvv));

    }

    public void updatePaymentDetails(View view){
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
                    cvv.getText().toString().trim().length() == 3) {


                userDBHelper = new DBHelper(this);

                int val = userDBHelper.updatePaymentDetails(Integer.parseInt(paymentID), fullName.getText().toString(), cardNo.getText().toString(), Integer.parseInt(expMonth.getText().toString()), Integer.parseInt(expYear.getText().toString()), Integer.parseInt(cvv.getText().toString()));

                if (val > 0) {
                    Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(this, PaymentConfirmActivity.class);
                    intent.putExtra("PaymentID", paymentID);
                    intent.putExtra("userID",userID);
                    startActivity(intent);

                } else {
                    Toast.makeText(this, "Data Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
            else{

                //Validation Alert

                AlertDialog alertDialog = new AlertDialog.Builder(this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Payment Error!!")
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

    public void removePaymentDetails(View view){

        AlertDialog alertDialog = new AlertDialog.Builder(this)
//set icon
                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                .setTitle("Warning!")
//set message
                .setMessage("Payment details will be removed.")
//set positive button
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what would happen when positive button is clicked
                        removePayment();



                    }
                })
//set negative button
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                })
                .show();




    }

    public void removePayment(){
        userDBHelper = new DBHelper(this);

        userDBHelper.deletePaymentDetails(Integer.parseInt(paymentID));

        Intent intent = new Intent(this, PaymentSelectActivity.class);
        intent.putExtra("userID",userID);
        startActivity(intent);
    }



}