package com.android.uas1;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHandler extends SQLiteOpenHelper {
    //database name
    public static final String DATABASE_NAME = "AngkaTerkecil";
    public static final int DATABASE_VERSION = 1;
    //Table Name
    public static final String TABLE_NAME = "Table_Angka";

    //column Table
    public static final String KEY_ID =  "id";
    public static final String NUMBER = "Angka_Terkecil";

    public DatabaseHandler( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqDb) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+ NUMBER + " INTEGER" + ")";
        sqDb.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqDb, int i, int i1) {
        sqDb.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqDb);
    }


}
