package com.example.gebruiker.ac_numbertrivia.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.gebruiker.ac_numbertrivia.Adapters.MyNumberQuoteRecyclerViewAdapter;
import com.example.gebruiker.ac_numbertrivia.Models.NumberQuoteItem;
import com.example.gebruiker.ac_numbertrivia.R;
import com.example.gebruiker.ac_numbertrivia.Utils.NumbersApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView mNumberTextView;
    public List<NumberQuoteItem> numberQuoteItems = new ArrayList<>();
    private RecyclerView mRc;
    private MyNumberQuoteRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView mRV = findViewById(R.id.list);
        RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);
        mRV.setLayoutManager(mLayoutManager);

        mAdapter = new MyNumberQuoteRecyclerViewAdapter(numberQuoteItems);
        mRV.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestData();
            }
        });
    }

    private void requestData()

    {
        NumbersApiService service = NumbersApiService.retrofit.create(NumbersApiService.class);

        Call<NumberQuoteItem> call = service.getTodaysNumber();
        call.enqueue(new Callback<NumberQuoteItem>() {

            @Override
            public void onResponse(Call<NumberQuoteItem> call, Response<NumberQuoteItem> response) {
                System.out.println(response + "!!!!!!!!!!!!!");
                NumberQuoteItem numberQuoteItem = response.body();
                numberQuoteItems.add(numberQuoteItem);
                updateUI();
            }

            @Override
            public void onFailure(Call<NumberQuoteItem> call, Throwable t) {
                Log.d("error", t.toString());
            }
        });

    }

    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new MyNumberQuoteRecyclerViewAdapter(numberQuoteItems);
            mRc.setAdapter(mAdapter);
        } else {
            mAdapter.swapList(numberQuoteItems);
        }
    }
}
