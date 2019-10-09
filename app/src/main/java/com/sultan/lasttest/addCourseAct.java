package com.sultan.lasttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class addCourseAct extends AppCompatActivity  {

   List<Course> course = new ArrayList<>();
    Button showSpiner;

    Button btn;
    Teacher teacher;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    String id;
    final String TAG="addCourseAct";
    boolean checkStudnet = true;
    String doc = auth.getUid();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        btn =(Button)findViewById(R.id.btnaddcourse) ;
        course = (List<Course>) getIntent().getSerializableExtra("c");
        Spinner spinner = (Spinner) findViewById(R.id.spinnerAddCourse);
        mySpinner adapter = new mySpinner(course,addCourseAct.this);
        spinner.getPaddingStart();
        spinner.setAdapter(adapter);

       db.collection("student").document(doc).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

               if(task.isSuccessful()){
                   DocumentSnapshot d = task.getResult();
                   id = d.get("StudentID").toString();
               }
            }
        });



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int postion, long l) {
                 String s = course.get(postion).teacherUID;
                final String courseID = course.get(postion).courseID;

                db.collection("teacher").document(s).get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot documentSnapshot = task.getResult();
                                    teacher = documentSnapshot.toObject(Teacher.class);
                                    TextView t =(TextView)findViewById(R.id.txtTeachername);
                                    t.setText(teacher.name + " "+teacher.lastName);


                                }

                            }
                        });

           btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        db.collection("course").document(courseID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                DocumentSnapshot d = task.getResult();
                                Course c = new Course();
                                c = d.toObject(Course.class);
                                for(int i=0; i <c.studentUID.size();i++){
                                    if(c.studentUID.get(i).equals(id)){
                                        Toast.makeText(getApplicationContext(),"You are in course",Toast.LENGTH_LONG).show();
                                        checkStudnet=false;


                                    }
                                }
                                if(checkStudnet){
                                    Map<String, Object> add = new HashMap<>();
                                    add.put("studentUID", FieldValue.arrayUnion(id));
                                    db.collection("course").document(c.courseID)
                                            .update(add);
                                    Map<String, Object> add1 = new HashMap<>();
                                    add1.put("course", FieldValue.arrayUnion(c.courseID));

                                    db.collection("student").document(doc)
                                            .update(add1);
                                    Toasty.success(getApplicationContext(),"the course is added",Toast.LENGTH_LONG).show();

                                }

                            }
                        });



                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }






}