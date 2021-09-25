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


public class UserLoginActivity extends AppCompatActivity {

    DBHelper userDBHelper;
    SQLiteDatabase sqLiteDatabase;
    String username, password, userID;
    EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        etUsername = findViewById(R.id.et_userLoginUsername);
        etPassword = findViewById(R.id.et_userLoginPassword);


    }
    public void login(View view){
        try {
            if(etUsername.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()){
                //Alert

                AlertDialog alertDialog = new AlertDialog.Builder(this)
    //set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
    //set title
                        .setTitle("Error")
    //set message
                        .setMessage("Please fill all fields")

    //set negative button
                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
            }
            else{



                    userDBHelper = new DBHelper(getApplicationContext());
                    sqLiteDatabase = userDBHelper.getReadableDatabase();
                    Cursor cursor = userDBHelper.getUser(etUsername.getText().toString(), sqLiteDatabase);

                    if(cursor.moveToFirst()){
                        password = cursor.getString(0);
                        userID = cursor.getString(1);
                    }

                    System.out.println(userID+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    System.out.println(password+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                    if(password.equals(etPassword.getText().toString())){
                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, PaymentCheckout.class);
                        intent.putExtra("userID",userID);
                        startActivity(intent);
                    }
                    else{
                        //Alert

                        AlertDialog alertDialog = new AlertDialog.Builder(this)
        //set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
        //set title
                                .setTitle("Login Error")
        //set message
                                .setMessage("Username or Password is incorrect")

        //set negative button
                                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                })
                                .show();
                    }


            }
        } catch (Exception e) {
            //Alert

            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    //set icon
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    //set title
                    .setTitle("Login Error")
                    //set message
                    .setMessage("Username or Password is incorrect")

                    //set negative button
                    .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .show();
                etUsername.setText("");
                etPassword.setText("");


        }


    }

    public void navigatetoRegister(View view){
        Intent intent = new Intent(this, UserRegistration.class);
        startActivity(intent);
    }
}