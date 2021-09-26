package com.example.foodiemobileapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class update_details extends AppCompatActivity {

        EditText description,price;
        Button update;
        DBHelper dbHelper;
        Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);

            description = findViewById(R.id.et_updateDescription);
            price = findViewById(R.id.et_updatePrice);
            update = findViewById(R.id.btn_adminUdateItemOneRow);

            context = this;
            dbHelper = new DBHelper(context);

            //get id value from admin home page
            final String id = getIntent().getStringExtra("id");
            UserMaster userMaster = dbHelper.getSingle(Integer.parseInt(id));

            //get values to text fields from database
            description.setText(userMaster.getDescription());
            price.setText(userMaster.getPrice());

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String descriptionText = description.getText().toString();
                    String priceText = price.getText().toString();

                    UserMaster userMaster = new UserMaster(Integer.parseInt(id),descriptionText,priceText);
                    Intent navHome = new Intent(getApplicationContext(),admin_home.class);
                    int state = dbHelper.updateSingleItem(userMaster);
                    startActivity(navHome);
                }
            });


    }
}

























