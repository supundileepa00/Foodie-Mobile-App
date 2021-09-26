package com.example.foodiemobileapp;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class listTest extends AppCompatActivity {

    ListView listView;

    String title[] = {"Biriyani","Burger","pizza","Submarine","Noodless","Drink"};

    int image[] = {R.drawable.biriyani, R.drawable.burger, R.drawable.pizza, R.drawable.submarine, R.drawable.noodless, R.drawable.drinks};

//    Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_test);
        listView = findViewById(R.id.listView_subCategory);
        CustomeAdapter adapter = new CustomeAdapter(this,title,image);
        listView.setAdapter(adapter);
//        toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle(getResources().getString(R.string.ToolBar_Text));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(listTest.this, com.example.mad.subCategory.class);
//                intent.putExtra("title", listView.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });

    }

}

class CustomeAdapter extends ArrayAdapter<String> {

    //pass values to array adapter
    Context context;
    String[] title;
    int[] images;
    CardView cView;


    CustomeAdapter(Context context, String[] title, int[] images){
        super(context,R.layout.single_row,R.id.tv_subCategory,title);
        this.context = context;
        this.images = images;
        this.title = title;
//        this.cView = cView;

    }


    //design single row view
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = inflater.inflate(R.layout.single_row,parent, false);

        //get acces from xml file
        ImageView imageView = row.findViewById(R.id.img_subCategoryImage);
        TextView titleView = row.findViewById(R.id.tv_subCategory);
//        Button btn = row.findViewById(R.id.btn_nextPage);
        //CardView view = row.findViewById(R.id.cardView_category);

        imageView.setImageResource(images[position]);
        titleView.setText(title[position]);

       // view.setCardBackgroundColor(000000);

        return row;
    }
}





























