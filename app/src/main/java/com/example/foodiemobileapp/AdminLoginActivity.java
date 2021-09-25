package com.example.foodiemobileapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AdminLoginActivity extends AppCompatActivity {

    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        username = findViewById(R.id.et_adminLoginusername);
        password = findViewById(R.id.et_adminLoginPassword);

    }

    public void adminLogin(View view){
        if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
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
        } else {
            if (username.getText().toString().equalsIgnoreCase("ADMIN") && password.getText().toString().equalsIgnoreCase("ADMIN")) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show();


            } else {
                //Alert

                AlertDialog alertDialog = new AlertDialog.Builder(this)
                        //set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        //set title
                        .setTitle("Login Error")
                        //set message
                        .setMessage("Invalid username or Password")

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