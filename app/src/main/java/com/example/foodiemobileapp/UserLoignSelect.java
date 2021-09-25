package com.example.foodiemobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class UserLoignSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_loign_select);
    }

    public void NavigatetoAdminLogin(View view){
        Intent intent = new Intent(this, AdminLoginActivity.class);
        startActivity(intent);
    }
    public void NavigatetoUserLogin(View view){
        Intent intent = new Intent(this, UserLoginActivity.class);
        startActivity(intent);
    }
}