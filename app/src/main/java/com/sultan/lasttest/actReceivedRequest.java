package com.sultan.lasttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.List;

public class actReceivedRequest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_received_request);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.requestRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        List<request> myDataset =  (List<request>) getIntent().getSerializableExtra("r");
        MyRequestesAdapter mAdapter = new MyRequestesAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }

}
