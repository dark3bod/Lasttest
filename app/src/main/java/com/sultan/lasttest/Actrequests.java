package com.sultan.lasttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class Actrequests extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actrequests);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.requestStudentRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        List<request> myDataset =  (List<request>) getIntent().getSerializableExtra("r");
        StudentAdapter mAdapter = new StudentAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }
}
