package com.sultan.lasttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class add_new_course extends AppCompatActivity {
    EditText courseName , courseSection , courseCode ;
    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
    TextView techerId;
    CardView addCourse;
    String section ,name , code;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_course);
        courseCode = findViewById(R.id.edttxtCourseCode);
        courseName = findViewById(R.id.edttxtCourseName);
        courseSection=findViewById(R.id.edttxtCourseSecion);
        techerId = findViewById(R.id.txtTeacherUID_addCourses);
        addCourse = findViewById(R.id.crdAddNewCourse);
        techerId.setText(id);

        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               name = courseName.getText().toString();
               code = courseCode.getText().toString();
               section = courseSection.getText().toString();



                db.collection("course").document(section).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                          if(task.getResult().exists()){
                              Toasty.error(getApplicationContext(),"there is secion like your section number").show();


                          }
                          else {
                              Map<String, Object> addDecomunt= new HashMap<>();
                              addDecomunt.put("courseCode",code);
                              addDecomunt.put("courseID", section);
                              addDecomunt.put("courseName",name);
                              addDecomunt.put("teacherUID",id);
                              addDecomunt.put("studentUID", Arrays.asList());
                              db.collection("course").document(section).set(addDecomunt)
                                      .addOnCompleteListener(new OnCompleteListener<Void>() {
                                          @Override
                                          public void onComplete(@NonNull Task<Void> task) {
                                              if (task.isSuccessful()) {
                                                  Map<String, Object> add = new HashMap<>();
                                                  add.put("course", FieldValue.arrayUnion(section));
                                                  db.collection("teacher").document(id)
                                                          .update(add);
                                                  Toasty.success(getApplicationContext(), "Your Course Have been added").show();
                                                  courseCode.setText("");
                                                  courseName.setText("");
                                                  courseSection.setText("");
                                              }
                                              else {
                                                  Toasty.error(getApplicationContext(),"Your Coures have not beet add please try again later").show();
                                              }

                                          }
                                      });


                          }

                        }
                        else {
                            Toasty.error(getApplicationContext(),"Please try again later");

                        }


                    }
                });






            }
        });



    }
}
