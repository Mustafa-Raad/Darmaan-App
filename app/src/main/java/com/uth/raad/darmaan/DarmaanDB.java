package com.uth.raad.darmaan;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by raad on 2/1/2018.
 */

public class DarmaanDB extends SQLiteOpenHelper {
    public final static String DBName = "DarmaanAppDB.db";
    public final static String TableName = "Doc_Map_Table";
    public final static String C1 = "ID";
    public final static String C2 = "Name";
    public final static String C3 = "Phone";
    public final static String C4 = "LatPoint";
    public final static String C5 = "LngPoint";

    public DarmaanDB(Context context) {
        super(context, DBName, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " +TableName+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, PHONE TEXT, LATPOINT REAL, LNGPOINT REAL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TableName);
        onCreate(sqLiteDatabase);
    }

    // retrive data from database
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor resource = db.rawQuery("SELECT * FROM "+TableName+" WHERE "+C1+" = ", null);
        return resource;
    }
}
