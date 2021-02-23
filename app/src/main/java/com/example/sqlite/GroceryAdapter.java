package com.example.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder> {

    Context mContext;
    Cursor mCursor;

    public GroceryAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    @NonNull
    @Override
    public GroceryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mContext);
        View view=inflater.inflate(R.layout.recycler_item,parent,false);
        return new GroceryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryViewHolder holder, int position) {

        if (!mCursor.moveToPosition(position)){
            return;
        }
        String name=mCursor.getString(mCursor.getColumnIndex(Grocery_Contract.Grocery_Entry.column_name));
        int amount=mCursor.getInt(mCursor.getColumnIndex(Grocery_Contract.Grocery_Entry.column_amount));

            holder.nameText.setText(name);
            holder.counttext.setText(String.valueOf(amount));
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor)
    {
        if (mCursor!=null){
            mCursor.close();
        }
        mCursor=newCursor;
        if (newCursor!=null){
            notifyDataSetChanged();
        }
    }

    public class GroceryViewHolder extends RecyclerView.ViewHolder {

        TextView nameText, counttext;

        public GroceryViewHolder(View itemView) {
            super(itemView);
            nameText=itemView.findViewById(R.id.item_name);
            counttext=itemView.findViewById(R.id.amount);
        }
    }
}
