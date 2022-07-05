package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;

import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String Table_Name = "email_list";
    private static final String  col1 = "email";


    public DatabaseHelper(@Nullable Context context) {
        super(context, Table_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String createTable =  "CREATE TABLE " + Table_Name + " ( " + col1 +" TEXT)";
        db.execSQL("create table mytable ("
                + "email text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP Table IF EXISTS " + Table_Name);
        onCreate(db);
    }

    public boolean addData(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1, item);
        long result = db.insert(Table_Name, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("Select * From " + Table_Name, null);
        return data;
    }
}
