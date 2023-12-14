package com.example.cardio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public  static final String databaseName = "Signupnew.db";
    public DatabaseHelper ( @Nullable Context context ) { super(context, "Signupnew.db", null, 1);
    }


    @Override
    public void onCreate ( SQLiteDatabase MyDatabase ) {
        MyDatabase.execSQL("create Table users(username TEXT primary key, password TEXT , u_name TEXT)");

    }

    @Override
    public void onUpgrade ( SQLiteDatabase MyDatabase, int i, int i1 ) {
        MyDatabase.execSQL("drop table if exists users");


    }
    public  boolean insertData ( String username, String password ,String u_name ){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("u_name",u_name);
        long result = MyDatabase.insert("users", null, contentValues);

        if (result == -1) {
            return false;

        } else {
            return true;
        }

    }

    public Boolean checkusername ( String username ) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkusernamepassword ( String username, String password ) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }

}


