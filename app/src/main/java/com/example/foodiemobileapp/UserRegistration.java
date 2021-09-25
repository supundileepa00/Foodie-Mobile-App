package com.example.foodiemobileapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodiemobileapp.Database.DBHelper;


public class UserRegistration extends AppCompatActivity {

    EditText username,password,retypePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);


        username = findViewById(R.id.et_regUsername);
        password = findViewById(R.id.editTextTextPassword);
        retypePassword = findViewById(R.id.et_RetypePwd);
    }

    public void registerUser(View view){
        if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty() || retypePassword.getText().toString().isEmpty()){
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
        }else{
            if(password.getText().toString().equals(retypePassword.getText().toString())){
                DBHelper dbHelper = new DBHelper(this);

                dbHelper.addUser(username.getText().toString(), password.getText().toString());

                Intent intent2 = new Intent(this, UserLoignSelect.class);
                startActivity(intent2);
                Toast.makeText(this, "Account Created", Toast.LENGTH_LONG).show();
            }else{
                //Alert

                AlertDialog alertDialog = new AlertDialog.Builder(this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error")
//set message
                        .setMessage("Passwords Not matched")

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