package com.sultan.lasttest.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sultan.lasttest.database.Course;
import com.sultan.lasttest.R;
import com.sultan.lasttest.database.section;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class Courses extends AppCompatActivity {



    private  final  String TAG = "Courses";
    Button add;
    int num ;
    ArrayList<section>sections;
   ArrayList<Course>courseName = new ArrayList<>();
    int g =0;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String studentid;
    List<Course> courses  = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        add = (Button)findViewById(R.id.addCourse);

        studentid = Objects.requireNonNull(getIntent().getSerializableExtra("studentid")).toString();
        courses =  (List<Course>) getIntent().getSerializableExtra("f");

        CollectionReference courseref = db.collection("section");


        courseref.whereArrayContains("studentUID",studentid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                section x = document.toObject(section.class);
                                db.collection("course").document(x.CourseID)
                                        .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if(task.isSuccessful()){
                                            DocumentSnapshot d = task.getResult();
                                            courseName.add(Objects.requireNonNull(d).toObject(Course.class));

                                            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.courseRecyclerView);
                                            LinearLayoutManager layoutManager = new LinearLayoutManager(Courses.this);
                                            mRecyclerView.setLayoutManager(layoutManager);
                                            MyAdapter mAdapter = new MyAdapter(courseName);
                                            mRecyclerView.setAdapter(mAdapter);

                                        }
                                    }
                                });

                            }




                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }

                });



    }


    public void openAddCourseAct(View v){
        if (num>8){
            //if student have 8 courses this function will not open add courses activity
            Toasty.info(getApplicationContext(), "لا تستطيع اضافة اكثر من 7 مقررات").show();
        }else{
        Intent intent = new Intent(getApplicationContext(), addCourseAct.class);
        intent.putExtra("c", (ArrayList<Course>)courses);
        startActivity(intent);}
    }




}
