package com.example.gebruiker.ac_numbertrivia.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gebruiker.ac_numbertrivia.Models.NumberQuoteItem;
import com.example.gebruiker.ac_numbertrivia.R;
import java.util.List;

public class MyNumberQuoteRecyclerViewAdapter extends RecyclerView.Adapter<MyNumberQuoteRecyclerViewAdapter.ViewHolder> {

    private List<NumberQuoteItem> mValues;

    public MyNumberQuoteRecyclerViewAdapter(List<NumberQuoteItem> items) {
        this.mValues = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.numberquote, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        System.out.println();
        final NumberQuoteItem x = mValues.get(position);

        holder.mNumber.setText(x.getNumber().toString());
        holder.mContentView.setText(x.getText());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNumber;
        public final TextView mContentView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNumber = view.findViewById(R.id.item_number);
            mContentView = view.findViewById(R.id.content);
        }
    }

    public void swapList (List<NumberQuoteItem> newList){
        mValues = newList;
        if(newList!= null) {
            this.notifyDataSetChanged();
        }

    }
}


