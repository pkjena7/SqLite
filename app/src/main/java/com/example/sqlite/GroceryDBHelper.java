package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import com.example.sqlite.groceryList.Grocery_Contract.*;
import com.example.sqlite.Grocery_Contract.*;

import androidx.annotation.Nullable;

public class GroceryDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "groceryList.db";
    public static final int DATABASE_VERSION = 1;

    public GroceryDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_GROCERYLIST_TABLE = "CREATE TABLE " +
                Grocery_Entry.table_name + " (" +
                Grocery_Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Grocery_Entry.column_name + " TEXT NOT NULL, " +
                Grocery_Entry.column_amount + " INTEGER NOT NULL, " +
                Grocery_Entry.column_timestamp + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";

        sqLiteDatabase.execSQL(SQL_CREATE_GROCERYLIST_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Grocery_Entry.table_name);
        onCreate(sqLiteDatabase);
    }
}
