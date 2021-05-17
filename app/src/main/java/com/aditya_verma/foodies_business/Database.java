package com.aditya_verma.foodies_business;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper{

    private	static final int DATABASE_VERSION =	5;
    private	static final String	DATABASE_NAME = "food_shop";
    private	static final String TABLE_FOOD = "food_shop_table";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PASSWORD = "password";



    public Database(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String	CREATE_CONTACTS_TABLE = "CREATE	TABLE " + TABLE_FOOD+ "(" + COLUMN_ID + " TEXT PRIMARY KEY," +COLUMN_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        onCreate(db);
    }

//    public void addModel(Query query){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_NAME, String.valueOf(query));
////        values.put(COLUMN_MOBILE, model.getMobile());
////        values.put(COLUMN_NEAR_AREA ,model.getNear_area());
////        values.put(COLUMN_ADDRESS, model.getAddress());
////        values.put(COLUMN_LOCATION_TEXT, model.getLocation_text());
////        values.put(COLUMN_TOTAL_BILL_PRICE, model.getTotal_bill_price());
////        values.put(COLUMN_MODE_OF_PAYMENT, model.getMode_of_payment());
////        values.put(COLUMN_DATE, model.getDate());
//
//        db.insert(TABLE_FOOD,null, values);
//    }


//    public ArrayList<Model> get_all_data(){
//        String sql = "select * from " + TABLE_FOOD;
//        SQLiteDatabase db = this.getReadableDatabase();
//        ArrayList<Model> storeModel = new ArrayList<>();
//        Cursor cursor = db.rawQuery(sql,null);
//        if(cursor.moveToFirst()){
//            do {
//                int id = cursor.getInt(0);
//                String flavour = cursor.getString(1);
//                String price = cursor.getString(2);
//                String quantity = cursor.getString(3);
//                byte[] image = cursor.getBlob(4);
//                storeModel.add(new Model(id,flavour,price,quantity,image));
//            }
//            while (cursor.moveToNext());
//        }
//        cursor.close();
//        return storeModel;
//    }

    public void insertData(String id,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID,id);
        contentValues.put(COLUMN_PASSWORD,password);
        db.insert(TABLE_FOOD,null,contentValues);
    }

    public Cursor get_poora_Data(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("select * from " + TABLE_FOOD, null);
        return cur;
    }

//
//    public ArrayList<Model> get_all_data(){
//        String sql = "select * from " + TABLE_FOOD;
//        SQLiteDatabase db = this.getReadableDatabase();
//        ArrayList<Model> storeModel = new ArrayList<>();
//        Cursor cursor = db.rawQuery(sql,null);
//        if(cursor.moveToFirst()){
//            do {
//                int id = cursor.getInt(0);
//                String flavour = cursor.getString(1);
//                String price = cursor.getString(2);
//                String quantity = cursor.getString(3);
//                byte[] image = cursor.getBlob(4);
//                storeModel.add(new Model(id,flavour,price,quantity,image));
//            }
//            while (cursor.moveToNext());
//        }
//        cursor.close();
//        return storeModel;
//    }


    public int get_count_cart(){
        String sql = "select * from " + TABLE_FOOD;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

//    public void updateModel(Model model){
//        ContentValues values = new ContentValues();
//        //values.put(COLUMN_IMAGE,model.getB());
//        values.put(COLUMN_FLAVOUR, model.getFlavour());
//        values.put(COLUMN_PRICE, model.getPrice());
//        values.put(COLUMN_QUANTITY,model.getQuantity());
//        values.put(COLUMN_IMAGE,model.getImage());
//        SQLiteDatabase db = this.getReadableDatabase();
//        db.update(TABLE_FOOD, values, COLUMN_ID	+ "	= ?", new String[] { String.valueOf(model.getId())});
//    }

    public void deleteModel(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FOOD, COLUMN_ID	+ "	= ?", new String[] { String.valueOf(id)});
    }

    public void delete_all_Model(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FOOD,null, null);
        db.close();
    }

//    public void delete_poora_Model(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_FOOD,null, null);
//        db.close();
//    }
}