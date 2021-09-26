package com.example.foodiemobileapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import com.example.foodiemobileapp.Database.DBHelper;


public class address extends AppCompatActivity {


    EditText name,city,address,postal;
    Button save,update;
    DBHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        name = findViewById(R.id.ed_name);
        city = findViewById(R.id.et_city);
        address = findViewById(R.id.et_address);
        postal = findViewById(R.id.et_post);

        save = findViewById(R.id.btn_save);
        update = findViewById(R.id.btn_update);
        db = new DBHelper(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                String cityTXT = city.getText().toString();
                String addressTXT = address.getText().toString();
                String postalTXT = postal.getText().toString();

                Boolean checkinsertdata = db.insertuserdata(nameTXT,cityTXT,addressTXT,postalTXT);
                if (checkinsertdata==true){
                    Toast.makeText(address.this, "New Address Added", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(address.this, "Added failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String cityTXT = city.getText().toString();
                String addressTXT = address.getText().toString();
                String postalTXT = postal.getText().toString();

                Boolean checkupdatedata = db.updateuserdata(nameTXT, cityTXT, addressTXT, postalTXT);
                if (checkupdatedata==true){
                    Toast.makeText(address.this, "New Address updateed", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(address.this, "update faild", Toast.LENGTH_SHORT).show();
                }


            }
        });




    }
}