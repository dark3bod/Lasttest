package com.sultan.lasttest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class addCourseAct extends AppCompatActivity  {

   List<Course> course = new ArrayList<>();
    Button showSpiner;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        btn =(Button)findViewById(R.id.btnaddcourse) ;
        course = (List<Course>) getIntent().getSerializableExtra("c");
        final Spinner spinner = (Spinner) findViewById(R.id.spinnerAddCourse);
       mySpinner adapter = new mySpinner(course,addCourseAct.this);
       spinner.getPaddingStart();
       spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int postion, long l) {
                final String s = course.get(postion).courseID;

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }





}
