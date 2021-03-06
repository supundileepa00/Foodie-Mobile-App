package com.example.foodiemobileapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "FoodieDB";
    private static final String TAG = "";
    private static final String TABLE_NAME = "FOOD_ITEM";

    //COLUMN NAMES
    private static final String ID = "id";
    private static final String CATEGORY = "category";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String PRICE = "price";
    //    private static final Blob IMAGE = "image";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
   // public DBHelper(Context context) {
     //   super(context, "Userdata.db", null, 1);
   // }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_UserTable = "CREATE TABLE " + UserMaster.Users.TABLE_NAME + "(" +
                UserMaster.Users.COLUMN_NAME_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                UserMaster.Users.COLUMN_NAME_USERNAME + " TEXT," +
                UserMaster.Users.COLUMN_NAME_PASSWORD + " TEXT)";


        String SQL_CREATE_CoinsTable = "CREATE TABLE " + UserMaster.Coins.TABLE_NAME + "(" +
                UserMaster.Coins.COLUMN_NAME_USERID + " INTEGER PRIMARY KEY REFERENCES "+ UserMaster.Users.TABLE_NAME + "(" +UserMaster.Users.COLUMN_NAME_USERID +")," +
                UserMaster.Coins.COLUMN_NAME_Coins + " INTEGER)";



        String SQL_CREATE_PaymentTable = "CREATE TABLE " + UserMaster.Payment.TABLE_NAME + "(" +
                UserMaster.Payment.COLUMN_NAME_PAYMENTID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                UserMaster.Payment.COLUMN_NAME_UserID + " INTEGER REFERENCES " + UserMaster.Users.TABLE_NAME + "(" +UserMaster.Users.COLUMN_NAME_USERID +")," +
                UserMaster.Payment.COLUMN_NAME_FULLNAME + " TEXT," +
                UserMaster.Payment.COLUMN_NAME_CARDNO + " TEXT," +
                UserMaster.Payment.COLUMN_NAME_ExpireMonth + " INTEGER," +
                UserMaster.Payment.COLUMN_NAME_ExpireYear + " INTEGER," +
                UserMaster.Payment.COLUMN_NAME_CVV + " INTEGER)";


        //table create query
        String TABLE_CREATE_QUERY = "CREATE TABLE "+TABLE_NAME+" " +
                "("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +CATEGORY+" TEXT,"
                +TITLE+" TEXT,"
                +DESCRIPTION+" TEXT,"
                +PRICE+" TEXT" +
//                +IMAGE+" blob" +
                ");";

        //run created query
        db.execSQL(TABLE_CREATE_QUERY);
        db.execSQL(SQL_CREATE_UserTable);
        db.execSQL(SQL_CREATE_CoinsTable);
        db.execSQL(SQL_CREATE_PaymentTable);

        db.execSQL("create Table userdetails(name TEXT primary key, city TEXT, address TEXT, postal TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Userdetails");

        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
        //drop older if existed
        db.execSQL(DROP_TABLE_QUERY);
        //create table again
        onCreate(db);
    }

    //Payment

    public void addUser(String username, String password){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.Users.COLUMN_NAME_USERNAME, username);
        values.put(UserMaster.Users.COLUMN_NAME_PASSWORD, password);

        db.insert(UserMaster.Users.TABLE_NAME, null, values);

    }

    public long addPaymentDetails(int userID, String fullName, String CardNo, int ExpMonth, int ExpYear, int cvv){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.Payment.COLUMN_NAME_UserID, userID);
        values.put(UserMaster.Payment.COLUMN_NAME_FULLNAME, fullName);
        values.put(UserMaster.Payment.COLUMN_NAME_CARDNO, CardNo);
        values.put(UserMaster.Payment.COLUMN_NAME_ExpireMonth, ExpMonth);
        values.put(UserMaster.Payment.COLUMN_NAME_ExpireYear, ExpYear);
        values.put(UserMaster.Payment.COLUMN_NAME_CVV, cvv);

        long rows = db.insert(UserMaster.Payment.TABLE_NAME, null, values);
        return rows;

    }



    public void deletePaymentDetails(int paymentID){
        SQLiteDatabase db = getReadableDatabase();

        String selection = UserMaster.Payment.COLUMN_NAME_PAYMENTID + " LIKE ?";

        String[] selectionArgs = { Integer.toString(paymentID)};

        db.delete(UserMaster.Payment.TABLE_NAME, selection, selectionArgs);

    }

    public int updatePaymentDetails(int paymentID, String fullName, String CardNo, int ExpMonth, int ExpYear, int cvv){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.Payment.COLUMN_NAME_FULLNAME, fullName);
        values.put(UserMaster.Payment.COLUMN_NAME_CARDNO, CardNo);
        values.put(UserMaster.Payment.COLUMN_NAME_ExpireMonth,ExpMonth);
        values.put(UserMaster.Payment.COLUMN_NAME_ExpireYear,ExpYear);
        values.put(UserMaster.Payment.COLUMN_NAME_CVV,cvv);

        String selection = UserMaster.Payment.COLUMN_NAME_PAYMENTID + " LIKE ?";
        String[] selectionArgs = {Integer.toString(paymentID)};

        int count = db.update(
                UserMaster.Payment.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        return count;

    }


    public Cursor getPaymentID(String fullName, SQLiteDatabase sqLiteDatabase){
        String[] projections = {UserMaster.Payment.COLUMN_NAME_PAYMENTID};
        String selection = UserMaster.Payment.COLUMN_NAME_FULLNAME+ " LIKE ?";
        String[] selectionArgs = {fullName};

        Cursor cursor = sqLiteDatabase.query(UserMaster.Payment.TABLE_NAME,projections,selection,selectionArgs,null,null,null);
        return cursor;
    }



    //payment ID is get As String
    public Cursor getPaymentDetails(String paymentID, SQLiteDatabase sqLiteDatabase){
        String[] projections = {UserMaster.Payment.COLUMN_NAME_FULLNAME, UserMaster.Payment.COLUMN_NAME_CARDNO, UserMaster.Payment.COLUMN_NAME_ExpireMonth, UserMaster.Payment.COLUMN_NAME_ExpireYear, UserMaster.Payment.COLUMN_NAME_CVV};
        String selection = UserMaster.Payment.COLUMN_NAME_PAYMENTID+ " LIKE ?";
        String[] selectionArgs = {paymentID};

        Cursor cursor = sqLiteDatabase.query(UserMaster.Payment.TABLE_NAME, projections,selection,selectionArgs, null,null,null);
        return cursor;
    }

    //get user details
    //fro userlogin
    public Cursor getUser(String username, SQLiteDatabase sqLiteDatabase){
        String[] projections = {UserMaster.Users.COLUMN_NAME_PASSWORD, UserMaster.Users.COLUMN_NAME_USERID};
        String selection = UserMaster.Users.COLUMN_NAME_USERNAME+ " LIKE ?";
        String[] selectionArgs = {username};
        Cursor cursor = sqLiteDatabase.query(UserMaster.Users.TABLE_NAME, projections,selection,selectionArgs,null,null,null);
        return cursor;
    }


    //Coins

    public long addCoins(int userID, int coins){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.Coins.COLUMN_NAME_USERID, userID);
        values.put(UserMaster.Coins.COLUMN_NAME_Coins, coins);

        long rows = db.insert(UserMaster.Coins.TABLE_NAME, null, values);
        return rows;
    }

    //get coins amount
    public Cursor getNoofCoins(String userID, SQLiteDatabase sqLiteDatabase){
        String[] projections = {UserMaster.Coins.COLUMN_NAME_Coins};
        String selection = UserMaster.Coins.COLUMN_NAME_USERID+ " LIKE ?";
        String[] selectionArgs = {userID};

        Cursor cursor = sqLiteDatabase.query(UserMaster.Coins.TABLE_NAME, projections,selection, selectionArgs, null,null,null);
        return cursor;
    }

    //update coins amount
    public int updateCoinsAmount(int userID, int coins){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(UserMaster.Coins.COLUMN_NAME_Coins, coins);

        String selection = UserMaster.Coins.COLUMN_NAME_USERID + " LIKE ?";
        String[] selectionArgs = {Integer.toString(userID)};


        int count = db.update(
                UserMaster.Coins.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        return count;

    }
    //cc
    public Boolean insertuserdata(String name, String city, String address, String postal){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("city",city);
        contentValues.put("address",address);
        contentValues.put("postal",postal);
        long result=db.insert("Userdetails", null, contentValues);
        if (result==-1){
            return false;

        }else {
            return true;
        }

    }

    //update

    public Boolean updateuserdata(String name, String city, String address, String postal){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("city",city);
        contentValues.put("address",address);
        contentValues.put("postal",postal);
        Cursor cursor = db.rawQuery("select * from Userdetails where name = ?", new String[] {name});
        if (cursor.getCount() > 0) {
            long result = db.update("Userdetails", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;

            } else {
                return true;
            }
        }else
        {
            return false;
        }
    }

    //display
    public List readAllInfo(String req){
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                "name",
                "city",
                "address",
                "postal",
                //OrderContract.OrderEntry.COLUMN_HASTOPPING,
                // OrderContract.OrderEntry.COLUMN_CREAM
        };
        String sortOrder = "name"+ " DESC";
        Cursor cursor = db.query(
                "Userdetails",
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
        List nameU = new ArrayList<>();
        List cityU = new ArrayList<>();
        List addressU = new ArrayList<>();
        List postalU = new ArrayList<>();
        // List creams = new ArrayList<>();


        while(cursor.moveToNext()){
            String Uname = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String Ucity = cursor.getString(cursor.getColumnIndexOrThrow("city"));
            String Uaddress =  cursor.getString(cursor.getColumnIndexOrThrow( "address"));
            String Upostal =  cursor.getString(cursor.getColumnIndexOrThrow("postal"));

            nameU.add(Uname);
            cityU.add(Ucity);
            addressU.add(Uaddress);
            postalU.add(Upostal);
            //creams.add(cream);
        }

        cursor.close();
        //  Log.i(TAG, "readAllInfo" + names);
        //  Log.i(TAG, "readAllInfo" + quantitties);
        // Log.i(TAG, "readAllInfo" + prices);
        //Log.i(TAG, "readAllInfo" + creams);

        if(req == "name"){
            return nameU;
        }else if(req == "city"){
            return cityU;
        }else if(req == "address"){
            return addressU;
        }else if(req =="postal"){
            return postalU;
        }
        else{
            return null;
        }

    }

    //add data to row by row
    public void addUserMaster(UserMaster userMaster){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        //structure the data and send data to the database
        ContentValues contentValues = new ContentValues();

        contentValues.put(CATEGORY,userMaster.getCategory());
        contentValues.put(TITLE,userMaster.getTitle());
        contentValues.put(DESCRIPTION,userMaster.getDescription());
        contentValues.put(PRICE,userMaster.getPrice());
//        contentValues.put(IMAGE,userMaster.getImage);
//        contentValues.put(IMAGE,byteImage);

        //save values to table
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        //close database
        sqLiteDatabase.close();
    }

    //get all food items into list
    public List<UserMaster> getAllFoodItems(){

        List<UserMaster> foodItems = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                //create new food object
                UserMaster userMaster = new UserMaster();
                userMaster.setId(cursor.getInt(0));
                userMaster.setCategory(cursor.getString(1));
                userMaster.setTitle(cursor.getString(2));
                userMaster.setDescription(cursor.getString(3));
                userMaster.setPrice(cursor.getString(4));

                foodItems.add(userMaster);
            }while(cursor.moveToNext());

        }
        return foodItems;
    }

    //delete items
    public void deleteItem(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,ID +" =?",new String[]{String.valueOf(id)});
        db.close();
    }

    //get a single item
    public UserMaster getSingle(int id){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor =  db.query(TABLE_NAME,new String[]{DESCRIPTION,PRICE},ID+"=?",new String[]{String.valueOf(id)},null,null,null);

        UserMaster userMaster;
        if(cursor != null){
            cursor.moveToFirst();
            String description = cursor.getString(3);
            String price = cursor.getString(4);
            userMaster = new UserMaster();

            return userMaster;
        };
        return null;
    }

    //update item details
    public int updateSingleItem(UserMaster userMaster){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DESCRIPTION,userMaster.getDescription());
        contentValues.put(PRICE,userMaster.getPrice());

        //get how many rows were updated
        int status = db.update(TABLE_NAME,contentValues,ID+" ?",new String[]{String.valueOf(userMaster.getId())});

        db.close();
        return status;
    }

}

