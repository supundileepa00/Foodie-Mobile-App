package com.example.foodiemobileapp;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class subCategory extends AppCompatActivity {

        ListView subListView;
        String subTitle[] = {};
        int subImage[] = {};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);


    }
}

class SubCategoryAdapter extends ArrayAdapter<String>{
    Context context;
    String[] subTitle;
    CardView cview;
    SubCategoryAdapter(Context context, String[] subTitle){
        super(context,R.layout.sub_category_single_row,R.id.tv_subCategory,subTitle);

        this.context = context;

//        @NonNull
//        @Override
//        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
//
//            inflater.inflate(R.layout.sub_category_single_row,parent,false);
//        }

   //     getView()
    }
}










































