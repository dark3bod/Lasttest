package com.sultan.lasttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sultan.lasttest.R;

import java.util.List;

public class Courses extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.courseRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        List<Course> myDataset =  (List<Course>) getIntent().getSerializableExtra("c");
        MyAdapter mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);


    }


}
