package com.example.foodiemobileapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.foodiemobileapp.Database.UserMaster;

import java.util.List;

public class UserMasterAdapter extends ArrayAdapter<UserMaster> {

    private  Context context;
    private int resource;
    List<UserMaster> userMasters;

    UserMasterAdapter(Context context, int resource, List<UserMaster> userMasters){
        super(context,resource,userMasters);

        this.context = context;
        this.resource = resource;
        this.userMasters = userMasters;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //set design and assign data from single view to list view
//        LayoutInflater inflater = LayoutInflater.from(context);

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.admin_one_row,parent,false);

        TextView title = row.findViewById(R.id.tv_adminOneRow);
//        Button update = row.findViewById(R.id.btn_adminUdateItemOneRow);
//        Button delete = row.findViewById(R.id.btn_adminDeleteItemOneRow);
//        ImageView image = row.findViewById(R.id.img_adminOneRow);
//        CardView view = row.findViewById(R.id.view_adminOneRow);

        UserMaster userMaster = userMasters.get(position);
        title.setText(userMaster.getTitle());

        return row;


    }
}











































