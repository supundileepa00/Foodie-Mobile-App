package com.example.foodiemobileapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import org.jetbrains.annotations.NotNull;

import static com.example.mad.R.*;

public class add_item extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ImageButton imageButton;
    private Spinner selectFootCategory;
    public static final int CAMERA_REQUEST=100;
    public static final int STORAGE_REQUEST=101;
    String cameraPermission[];
    String storagePermission[];

    TextView title,description,price;
    Button addItem;
    EditText inputTitle,inputDescription,inputPrice;

    //connect to DBHelper
    private DBHelper dbHelper;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_add_item);


        title = findViewById(id.tv_categoryName);
        description = findViewById(id.tv_add_item_description);
        price = findViewById(id.tv_add_item_price);
        addItem = findViewById(id.btn_add_new_item);
        inputTitle = findViewById(id.et_inputSubTitle);
        inputDescription = findViewById(id.et_inputDescription);
        inputPrice = findViewById(id.et_input_price);
        selectFootCategory = findViewById(id.spinner_selectCategory);

        context = this;
        dbHelper = new DBHelper(context);

        //navigate to admin home page && send dara to batabase
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get details from add item xml
                String userCategory = selectFootCategory.getSelectedItem().toString();
                String userTitle = inputTitle.getText().toString();
                String userDescription = inputDescription.getText().toString();
                String userPrice = inputPrice.getText().toString();

//                //get photo
//                String stringFilePath = Environment.getExternalStorageDirectory().getPath()+"/Downloads/"+inputTitle.getText().toString()+".jpeg";
//                Bitmap bitmap = BitmapFactory.decodeFile(stringFilePath);
//                //convert bitmap to byte format
//                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.PNG,0,byteArrayOutputStream);
//                byte[] byteImage = byteArrayOutputStream.toByteArray();


                if(userCategory.isEmpty() || userTitle.isEmpty() || userDescription.isEmpty() || userPrice.isEmpty() ){

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Warning");
                    builder.setMessage("Please fill all the fields");
                    builder.show();

                }else {
                    //object to UserMaster class and pass value to constructor
                    UserMaster userMaster = new UserMaster(userTitle, userDescription, userPrice, userCategory);
                    dbHelper.addUserMaster(userMaster);
                    Intent navHome = new Intent(add_item.this, admin_home.class);
                    startActivity(navHome);
                }
            }
        });


        //get spinner items
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, array.foodCategory, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectFootCategory.setAdapter(adapter);


        imageButton = findViewById(id.img_button);

        cameraPermission = new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        imageButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                int picd=0;
                if(picd==0){
                    if(!checkCameraPermission()){
                        requestCameraPermission();
                    }else{
                        pickFromGallery();
                    }
                }else if(picd==1){
                    if(!checkSroragePermission()){
                        requestStoragePermission();
                    }else{
                        pickFromGallery();
                    }
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestStoragePermission() {
        requestPermissions(storagePermission,STORAGE_REQUEST);
    }

    private boolean checkSroragePermission() {
        boolean result = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void pickFromGallery() {
        CropImage.activity().start(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestCameraPermission() {
        requestPermissions(cameraPermission,CAMERA_REQUEST);
    }

    private boolean checkCameraPermission() {

        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case CAMERA_REQUEST:{
                if(grantResults.length>0){
                    boolean camera_accepted = grantResults[0]==(PackageManager.PERMISSION_GRANTED);
                    boolean storage_accepted = grantResults[1]==(PackageManager.PERMISSION_GRANTED);
                    if(camera_accepted && storage_accepted){
                        pickFromGallery();
                    }else{
                        Toast.makeText(this,"Please enable camera and stoarage permission",Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
            case STORAGE_REQUEST:{
                if(grantResults.length>0){
                   boolean storage_accepted =  grantResults[0]==(PackageManager.PERMISSION_GRANTED);
                   if(storage_accepted){
                       pickFromGallery();
                   }else {
                       Toast.makeText(this,"please enable storage permission",Toast.LENGTH_SHORT).show();
                   }
                }
            }
            break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if(resultCode==RESULT_OK){
                Uri resultUri = result.getUri();
                Picasso.with(this).load(resultUri).into(imageButton);
            }
        }
        selectFootCategory.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            String choice = adapterView.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}



























































