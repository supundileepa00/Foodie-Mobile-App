package com.example.foodiemobileapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodiemobileapp.Database.DBHelper;
import com.example.foodiemobileapp.Database.UserMaster;

import java.util.ArrayList;
import java.util.List;

import static com.example.foodiemobileapp.R.layout.*;

public class admin_home extends AppCompatActivity {

    Button addItem;
    ListView listView;
    Context context;
    private DBHelper dbHelper;
    private List<UserMaster> userMasters;

    Button update;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        update = findViewById(R.id.btn_adminUdateItemOneRow);
        delete = findViewById(R.id.btn_adminDeleteItemOneRow);
        DBHelper dbHelper = new DBHelper(context);
        addItem = findViewById(R.id.btn_addItem);
//        listView = findViewById(R.id.list_admin_Home);
        userMasters = new ArrayList<>();

        //get data from database
        userMasters = dbHelper.getAllFoodItems();
//        updateItem = findViewById(R.id.btn_adminUdateItemOneRow);
//        deleteItem = findViewById(R.id.btn_adminDeleteItemOneRow);
        UserMasterAdapter userMasterAdapter = new UserMasterAdapter(context,admin_one_row,userMasters);

        listView.setAdapter(userMasterAdapter);
        //navigate to item adding page
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addingPage = new Intent(getApplicationContext(),add_item.class);
                startActivity(addingPage);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //get values from array
                UserMaster userMaster = userMasters.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Choose Option");
                builder.show();
                builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                                Intent navAdmin = new Intent(getApplicationContext(),admin_home.class);
                                startActivity(navAdmin);
                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.deleteItem(userMaster.getId());
                        Intent navAdmin = new Intent(getApplicationContext(),admin_home.class);
                        startActivity(navAdmin);
                    }
                });
                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent navUpdate = new Intent(getApplicationContext(),update_details.class);
                        navUpdate.putExtra("id",String.valueOf(userMaster.getId()));
                        startActivity(navUpdate);
                    }
                });
                builder.show();

            }
        });

    }
}



























