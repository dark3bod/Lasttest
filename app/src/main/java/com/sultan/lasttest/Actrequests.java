package com.sultan.lasttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

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

