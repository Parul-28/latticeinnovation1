package com.example.signup;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



    public class DatabaseHelper extends SQLiteOpenHelper {
        public static final String DATABASE_NAME="register.db";
        public static final String TABLE_NAME="registeration";
        public static final String COL_1="ID";
        public static final String COL_2="FirstName";
       ;
        public static final String COL_3="Password";
        public static final String COL_4="Email";
        public static final String COL_5="Phone";
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,FirstName TEXT,Password TEXT,Email TEXT,Phone TEXT)");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE " +TABLE_NAME); //Drop older table if exists
            onCreate(db);
        }
    }

