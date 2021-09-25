package com.example.foodiemobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.foodiemobileapp.Database.DBHelper;
import com.example.foodiemobileapp.Database.OrderHelper;

import java.util.List;

public class checkout extends AppCompatActivity {
    private Button button, btnTake;


    String total;

    TextView listItems,listPrices,listQty,finalCost,addressF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        button = (Button) findViewById(R.id.btn_adddelevry);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendelevery();
            }
        });
        listItems = findViewById(R.id.txt_orderNames);
        listPrices = findViewById(R.id.txt_checkoutPrices);
        listQty = findViewById(R.id.txt_checkoutqty);
        finalCost = findViewById(R.id.txt_finalCost);
        addressF = findViewById(R.id.txt_address);

        btnTake = findViewById(R.id.btn_takeaway);

        OrderHelper ord = new OrderHelper(this);
        List names  = ord.readAllInfo("name");
        List qty = ord.readAllInfo("quantity");
        List prices = ord.readAllInfo("price");
        List hastoppings = ord.readAllInfo("hastoppings");
        List creams = ord.readAllInfo("creams");

        //DatabaseHelper db = DatabaseHelper.getInstance(this);
      //  textView.setText("" + db.sumPriceCartItems());

        OrderHelper ord_t = new OrderHelper(this);
        finalCost.setText(" "+ord_t.sumPriceCartItems());






        //get order name
        String namesArray[] = new String[names.size()];
        namesArray = (String[]) names.toArray(namesArray);

        for(int i=0; i<namesArray.length; i++){
            listItems.setText(namesArray[i] + "\n");
        }

        //get price
        String priceArray[] = new String[names.size()];
        priceArray = (String[]) prices.toArray(priceArray);

        for(int i=0; i<priceArray.length; i++){
            listPrices.setText(priceArray[i] + "\n");
        }

        String qtyArray[] = new String[qty.size()];
        qtyArray = (String[]) qty.toArray(qtyArray);

        for(int i=0; i<qtyArray.length; i++){
            listQty.setText(qtyArray[i] + "\n");
        }

        //total
       // public int getSumValue(){

        DBHelper Userdetails = new DBHelper(this);
        List add = Userdetails.readAllInfo("address");

        String addressArray[] = new String[add.size()];
        addressArray = (String[]) add.toArray(addressArray);

        for(int i=0; i<qtyArray.length; i++) {
            addressF.setText(addressArray[i] + "\n");
        }

        btnTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }

        });















        // for(Object element: names){
        //    listItems.setText(element+"\n");
       // }
        //for(Object element: prices){
        //    listPrices.setText(element+"\n");
        //}

    }
    public void opendelevery(){
        Intent intent = new Intent(this, address.class);
        startActivity(intent);
    }
    public void openDialog(){
        tackDialog tackDialog = new tackDialog();
        tackDialog.show(getSupportFragmentManager(), "tackDialog");

    }





}