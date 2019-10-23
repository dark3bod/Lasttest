package com.sultan.lasttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sultan.lasttest.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Courses extends AppCompatActivity {



    private  final  String TAG = "Courses";
    Button add;
    ArrayList<Course>courseName;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String studentid;
    List<Course> courses  = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        add = (Button)findViewById(R.id.addCourse);

        studentid = getIntent().getSerializableExtra("studentid").toString();
        courses =  (List<Course>) getIntent().getSerializableExtra("f");
        courseName= new ArrayList<>();
        CollectionReference courseref = db.collection("course");


        courseref.whereArrayContains("studentUID",studentid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Course course = new Course();
                                course = document.toObject(Course.class);
                                courseName.add(course);

                            }
                            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.courseRecyclerView);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(Courses.this);
                            mRecyclerView.setLayoutManager(layoutManager);
                            MyAdapter mAdapter = new MyAdapter(courseName);
                            mRecyclerView.setAdapter(mAdapter);



                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });





    }


    public void openAddCourseAct(View v){
        Intent intent = new Intent(getApplicationContext(), addCourseAct.class);
        intent.putExtra("c", (ArrayList<Course>)courses);
        startActivity(intent);
    }



}
