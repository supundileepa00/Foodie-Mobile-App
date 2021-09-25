package com.example.foodiemobileapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Model> modelList;
    RecyclerView recyclerView;
    OrderAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // creating an arraylist

        modelList = new ArrayList<>();
        modelList.add(new Model("Green Tea2", getString(R.string.greentea), R.drawable.greentea ));

        //1   modelList.add(new Model("Latte", getString(R.string.latte), R.drawable.late));
        // 1   modelList.add(new Model("Orange Smoothie", getString(R.string.orangesmoothie), R.drawable.orange));
        //1   modelList.add(new Model("Orange Vanilla", getString(R.string.orangevanilla), R.drawable.orangevanilla));
        //1    modelList.add(new Model("Cappucino", getString(R.string.cappcuni), R.drawable.cappcunio));
        //1    modelList.add(new Model("Thai Tea", getString(R.string.thaitea), R.drawable.thaitea));
        //1    modelList.add(new Model("Tea", getString(R.string.tea), R.drawable.tea));
        //1    modelList.add(new Model("Bubble Tea", getString(R.string.bubbletea), R.drawable.milk));
        //1    modelList.add(new Model("Matcha", getString(R.string.match), R.drawable.match));

        // recyclerview
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));
        // adapter
        mAdapter = new OrderAdapter(this, modelList);
        recyclerView.setAdapter(mAdapter);




    }
}