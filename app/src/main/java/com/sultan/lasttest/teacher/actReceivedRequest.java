package com.sultan.lasttest.teacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sultan.lasttest.R;
import com.sultan.lasttest.database.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class actReceivedRequest extends AppCompatActivity {

    String teacherid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<request> myDataset = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // activity requests for teacher
        setContentView(R.layout.activity_act_received_request);
        //Toasty.success(getApplicationContext(),teacherid).show();
        db.collection("request").whereEqualTo("TeacherID",teacherid)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot q : Objects.requireNonNull(task.getResult())){

                        if(q.get("status").toString().equals("0")){
                            request r = new request();
                            r.reqID=q.get("reqID").toString();

                            r.CourseID=q.get("CourseID").toString();
                            r.Date=q.get("Date").toString();
                            r.problem = q.get("problem").toString();
                            r.StudentID=q.get("StudentID").toString();
                            r.status=q.get("status").toString();
                            r.TeacherID=q.get("TeacherID").toString();
                            r.Time=q.get("Time").toString();
                            r.ID=q.getId();
                            myDataset.add(r);

                        }


                    }
                    RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.requestRecyclerView);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(actReceivedRequest.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    MyRequestesAdapter mAdapter = new MyRequestesAdapter(myDataset);
                    mRecyclerView.setAdapter(mAdapter);
                }

            }
        });

    }

}
