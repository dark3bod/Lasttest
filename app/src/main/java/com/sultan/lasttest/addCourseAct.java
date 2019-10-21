package com.sultan.lasttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class addCourseAct extends AppCompatActivity  {

   List<Course> course = new ArrayList<>();
   SpinnerDialog spinnerDialog;
   TextView txtsearchable ;


    Button btn , btntable;
    Teacher teacher;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    String id;
    final String TAG="addCourseAct";
    boolean checkStudnet = true;
    String doc = auth.getUid();
    AutoCompleteTextView multiAutoCompleteTextView;
    ArrayList<String> courseName = new ArrayList<>() ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_add_course);
        btn =(Button)findViewById(R.id.btnaddcourse) ;

        course = (List<Course>) getIntent().getSerializableExtra("c");

       db.collection("student").document(doc).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

               if(task.isSuccessful()){
                   DocumentSnapshot d = task.getResult();
                   id = d.get("StudentID").toString();
               }
            }
        });



       for (int i=0;i<course.size();i++){
           courseName.add(course.get(i).courseName + "("+course.get(i).courseID+")");

       }
        txtsearchable = findViewById(R.id.txtsearchable);
        spinnerDialog = new SpinnerDialog(addCourseAct.this, courseName,"Select Cours");
        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                txtsearchable.setText(item);
                String x=item;


                for (int i =0;i<course.size();i++){
                    String temp = course.get(i).courseName + "("+course.get(i).courseID+")";
                    if(temp.equals(x)){
                        Toasty.success(getApplicationContext(),course.get(i).courseName).show();
                        String s = course.get(i).teacherUID;
                        final String courseID = course.get(i).courseID;
                        final TextView courseName = findViewById(R.id.txtCoursename);
                        final TextView courseCode = findViewById(R.id.txtCoursecode);
                        final TextView courseSection = findViewById(R.id.txtCoursesecion);
                        courseName.setText(" " +course.get(i).courseName+" ");
                        courseCode.setText(" " +course.get(i).courseCode+" ");
                        courseSection.setText(" " +course.get(i).courseID);
                        final TextView t =(TextView)findViewById(R.id.txtTeachername);

                        db.collection("teacher").document(s).get()
                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.isSuccessful()) {
                                            DocumentSnapshot documentSnapshot = task.getResult();
                                            teacher = documentSnapshot.toObject(Teacher.class);

                                            t.setText(" " +teacher.name + " "+teacher.lastName);


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
                                               Toasty.info(getApplicationContext(),"You are in course").show();
                                                checkStudnet=false;
                                                txtsearchable.setText("");
                                                txtsearchable.setHint("Choose Course:");
                                                courseCode.setText("");
                                                courseSection.setText("");
                                                courseName.setText("");
                                                t.setText("");


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
                                            txtsearchable.setText("");
                                            txtsearchable.setHint("اختر مقرر");
                                            courseCode.setText("");
                                            courseSection.setText("");
                                            courseName.setText("");
                                            t.setText("");

                                        }

                                    }
                                });



                            }
                        });


                    }
                }
            }
        });

        txtsearchable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinnerDialog.showSpinerDialog();
            }
        });


      /* multiAutoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.multiAutoCompleteTextView2);
        ArrayAdapter<String> ad = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,courseName);
        multiAutoCompleteTextView.setAdapter(ad);
        multiAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posion, long l) {
                String x=multiAutoCompleteTextView.getText().toString();


                for (int i =0;i<course.size();i++){
                    String temp = course.get(i).courseName + "("+course.get(i).courseID+")";
                    if(temp.equals(x)){
                        Toasty.success(getApplicationContext(),course.get(i).courseName).show();
                        String s = course.get(i).teacherUID;
                        final String courseID = course.get(i).courseID;

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
                }

            }
        });
*/


    }






}
