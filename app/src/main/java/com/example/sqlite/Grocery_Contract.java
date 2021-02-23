package com.example.sqlite;

import android.provider.BaseColumns;

public class Grocery_Contract {

    public Grocery_Contract() {
    }

    public static final class Grocery_Entry implements BaseColumns {
        public static final String table_name = "Grocery List";
        public static final String column_name = "Grocery List";
        public static final String column_amount = "Grocery List";
        public static final String column_timestamp = "Grocery List";
    }
}
