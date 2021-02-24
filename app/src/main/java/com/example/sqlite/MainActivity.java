package com.example.sqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    Button button1, button2, button3;
    int amount = 0;
    private SQLiteDatabase database;
    GroceryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GroceryDBHelper dbHelper = new GroceryDBHelper(this);
        database = dbHelper.getWritableDatabase();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GroceryAdapter(this, getAllItems());
        recyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                removeitem((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);

        editText = findViewById(R.id.edit_text);
        textView = findViewById(R.id.amount);
        button1 = findViewById(R.id.minus);
        button2 = findViewById(R.id.plus);
        button3 = findViewById(R.id.add);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrease();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increase();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });
    }

    private void increase() {
        amount++;
        textView.setText(String.valueOf(amount));
    }

    private void decrease() {
        if (amount > 0) {
            amount--;
            textView.setText(String.valueOf(amount));
        }
    }

    private void addItem() {
        if (editText.getText().toString().trim().length() == 0 || amount == 0) {
            return;
        }
        String name = editText.getText().toString();
        ContentValues cv = new ContentValues();
        cv.put(Grocery_Contract.Grocery_Entry.column_name, name);
        cv.put(Grocery_Contract.Grocery_Entry.column_amount, amount);

        database.insert(Grocery_Contract.Grocery_Entry.table_name, null, cv);
        adapter.swapCursor(getAllItems());
        editText.getText().clear();

    }

    private void removeitem(long id) {
        database.delete(Grocery_Contract.Grocery_Entry.table_name,
                Grocery_Contract.Grocery_Entry._ID + "=" + id, null);
        adapter.swapCursor(getAllItems());
    }

    private Cursor getAllItems() {
        return database.query(Grocery_Contract.Grocery_Entry.table_name,
                null,
                null,
                null,
                null,
                null,
                Grocery_Contract.Grocery_Entry.column_timestamp + " DESC");
    }
}