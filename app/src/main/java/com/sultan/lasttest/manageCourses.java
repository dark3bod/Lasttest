package com.sultan.lasttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class manageCourses extends AppCompatActivity {

    public List<Course>mData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_courses);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.TeachercourseRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mData =  (List<Course>) getIntent().getSerializableExtra("c");

        teacher_courses_adapter mAdapter = new teacher_courses_adapter(mData);
        mRecyclerView.setAdapter(mAdapter);


    }
    public void openAddNewCourseAct(View v){
        Intent intent = new Intent(manageCourses.this , add_new_course.class);
        startActivity(intent);
    }
}
