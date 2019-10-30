package com.sultan.lasttest.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.sultan.lasttest.database.Course;
import com.sultan.lasttest.R;
import com.sultan.lasttest.database.request;

import java.util.List;

public class Actrequests extends AppCompatActivity {

    public final String TAG = "Actrequests";
    List<Course> myDataset1;
    List<request> myDataset;










    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actrequests);
        myDataset = (List<request>) getIntent().getSerializableExtra("r");
        myDataset1 = (List<Course>) getIntent().getSerializableExtra("c");

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.requestStudentRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        StudentAdapter mAdapter = new StudentAdapter(myDataset, myDataset1);
        mRecyclerView.setAdapter(mAdapter);


    }




}

