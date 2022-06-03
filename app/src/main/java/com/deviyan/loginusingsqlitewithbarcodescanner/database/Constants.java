package com.deviyan.loginusingsqlitewithbarcodescanner.database;

public class Constants {

    //db name
    public static final String DB_NAME = "IOT";
    // db version
    public static final int DB_VERSION = 1;
    // db table
    public static final String TABLE_NAME = "UserAccount";

    //table column
    public static final String userID = "userID";
    public static final String userName = "username";
    public static final String userPassword = "password";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + userID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + userName + " TEXT,"
            + userPassword + " TEXT,"
            + ");";
}
