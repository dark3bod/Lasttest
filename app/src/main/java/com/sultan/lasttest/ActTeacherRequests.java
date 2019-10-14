package com.sultan.lasttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class ActTeacherRequests extends AppCompatActivity {
    List<request>myDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_teacher_requests);
        myDataset = (List<request>) getIntent().getSerializableExtra("r");
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.requestTeacherRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        TeacherAdapter mAdapter = new TeacherAdapter( myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }
}
