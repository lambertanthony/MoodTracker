package com.anthony.project.moodtracker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoricActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    SingletonMoodsData  singletonMoodsData;
    ArrayList<Mood> arrayHistoric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);
        recyclerView = (RecyclerView) findViewById(R.id.activity_history_recycler_view);
        singletonMoodsData = SingletonMoodsData.getInstance();
        arrayHistoric= singletonMoodsData.getArray();
        HistoricRecyclerAdapter  moodRecyclerAdapter = new HistoricRecyclerAdapter(this,arrayHistoric);
        recyclerView.setAdapter(moodRecyclerAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


    }
}