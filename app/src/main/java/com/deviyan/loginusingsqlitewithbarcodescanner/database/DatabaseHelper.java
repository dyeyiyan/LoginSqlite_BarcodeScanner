package com.deviyan.loginusingsqlitewithbarcodescanner.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_TABLE);
        Log.d("Table is created: " , Constants.CREATE_TABLE);
        Log.d("DB", "DB is created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);

        //create tables again
        onCreate(db);
    }

    //insert user account
    public long insertUserAccount(String username, String password) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.userName,username);
        values.put(Constants.userPassword, password);
        //values.put(Constants.userLogged, login);

        // Inserting Row
        long id = db.insert(Constants.TABLE_NAME, null, values);
        // Closing database connection
        db.close();
        return id;
    }

    // Getting user count
    public Cursor getData(){
        //Create and/or open a database that will be used for reading and writing
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from UserAccount ",null);
        return cursor;
    }

}
