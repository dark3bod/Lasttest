package com.sultan.lasttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class manageCourses extends AppCompatActivity {

    public List<Course>mData = new ArrayList<>();

    String teacherid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_courses);

        db.collection("course").whereEqualTo("teacherUID",teacherid)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){

                    for(QueryDocumentSnapshot q : task.getResult()){
                        mData.add(q.toObject(Course.class));
                    }
                    RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.TeachercourseRecyclerView);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(manageCourses.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    teacher_courses_adapter mAdapter = new teacher_courses_adapter(mData);
                    mRecyclerView.setAdapter(mAdapter);
                }

            }
        });





    }
    public void openAddNewCourseAct(View v){
        Intent intent = new Intent(manageCourses.this , add_new_course.class);
        startActivity(intent);
    }
}
