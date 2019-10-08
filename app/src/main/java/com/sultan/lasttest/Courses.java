package com.sultan.lasttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import com.sultan.lasttest.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Courses extends AppCompatActivity {


    Button add;
    List<Course> courses  = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        add = (Button)findViewById(R.id.addCourse);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.courseRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        List<Course> myDataset =  (List<Course>) getIntent().getSerializableExtra("c");
        courses =  (List<Course>) getIntent().getSerializableExtra("f");

        MyAdapter mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);



    }


    public void openAddCourseAct(View v){
        Intent intent = new Intent(getApplicationContext(), addCourseAct.class);
        intent.putExtra("c", (ArrayList<Course>)courses);
        startActivity(intent);
    }



}
