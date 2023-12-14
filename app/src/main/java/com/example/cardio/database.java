package com.example.cardio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper{

    private static final String dbname="fill_detail.db";

    public database ( @Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate ( SQLiteDatabase sqLiteDatabase ) {
        String q ="create table users(_id integer primary key autoincrement,name text, age text, mobiel text,gender text,full_report text)";
        sqLiteDatabase.execSQL(q);
    }

    @Override
    public void onUpgrade ( SQLiteDatabase sqLiteDatabase, int i, int i1 ) {
        sqLiteDatabase.execSQL("drop table if exists users");
        onCreate(sqLiteDatabase);
    }
    public boolean insert_data(String name,String age,String mobiel,String gender){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues c = new ContentValues();

        c.put("name",name);
        c.put("age",age);
        c.put("mobiel",mobiel);
        c.put("gender",gender);

        long r = db.insert("users",null,c);
        return r != -1;
    }
    public Cursor getdata() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users",null);
        return  cursor;
    }

    public boolean insert_data ( String full_report ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("Full_Report",full_report);
        long r = db.insert("users",null,c);
        return r != -1;

    }
    


}
