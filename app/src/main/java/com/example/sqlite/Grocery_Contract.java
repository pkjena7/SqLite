package com.example.sqlite;

import android.provider.BaseColumns;

public class Grocery_Contract {

    public Grocery_Contract() {
    }

    public static final class Grocery_Entry implements BaseColumns {
        public static final String table_name = "groceryList";
        public static final String column_name = "name";
        public static final String column_amount = "amount";
        public static final String column_timestamp = "timestamp";
    }
}
