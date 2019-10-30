package com.sultan.lasttest.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sultan.lasttest.database.Course;
import com.sultan.lasttest.R;
import com.sultan.lasttest.database.Student;
import com.sultan.lasttest.database.Teacher;
import com.sultan.lasttest.database.request;
import com.sultan.lasttest.database.section;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import es.dmoral.toasty.Toasty;

public class sendRequestAct extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    public static final String TAG = "sendRequestAct";
    private TextView dateText,timeText;
    public TimePickerDialog t1;
    int pos ,dayOfWeek ;
    Button s  ;
    public boolean dateLeget ,timeLeget ;
    List<String> reasonArray= new ArrayList<String>();
    //List<Teacher> teachers = new ArrayList<>();
    ArrayList<section>sectionName;
    Spinner reasonSpinner;

    List<Course> courses;
   TextView txtstudet , teachername ;
   EditText txtproblem;
   public Teacher t;
    request r;
    FirebaseFirestore db ;
    public String date ,time , reason;
    Student student;
    int i ;

    Spinner spinner1 ;
    ArrayList<Course>courseName;
    String studentid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {



        student = (Student)getIntent().getSerializableExtra("s") ;

          txtstudet=(TextView)findViewById(R.id.student_id);
          db =FirebaseFirestore.getInstance();

        super.onCreate(savedInstanceState);
        s =(Button)findViewById(R.id.btnSend);

        setContentView(R.layout.send_appointment_act);


       /* ArrayList<CharSequence> g = new ArrayList<>();
        int i = 0;
        for (Course c: courses
        ) {
            g.add(c.courseName);
        }*/
       db = FirebaseFirestore.getInstance();
        DocumentReference docRef;
        reasonArray.add(" ");
        reasonArray.add("مشكلة اجتماعية");
        reasonArray.add("بخصوص الاختبار");
        reasonArray.add("بخصوص الدرجات");
        reasonArray.add("مناقشة عامه");
        reasonArray.add("اخرى");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,  reasonArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reasonSpinner = (Spinner) findViewById(R.id.spnReaason);
        reasonSpinner.setAdapter(adapter);
        reasonSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               String select = adapterView.getSelectedItem().toString();
               txtproblem=(EditText) findViewById(R.id.txtproblem);
                if(select.equals("اخرى")){
                   txtproblem.setVisibility(View.VISIBLE);
               }else{
                   txtproblem.setVisibility(View.GONE);
                   reason = select;
               }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



     /*   i = 0 ;

        for( i = 0;i<courses.size();i++) {
            docRef = db.collection("teacher").document(courses.get(i).teacherUID);
            docRef.get().addOnCompleteListener(sendRequestAct.this, new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                                teachers.add(document.toObject(Teacher.class));
                        } else {
                            ///document doesnt exist
                            Log.d(TAG, "No such teacher");

                        }
                    } else {
                        ///task is not succsesful
                        Log.d(TAG, "get teacher failed with ", task.getException());

                    }
                }

            });
        }*/



        /*db.collection("student").document(studentid).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            student = task.getResult().toObject(Student.class);

                            for(i = 0 ; i < student.section.size();i++){
                                db.collection("course").whereArrayContains("sections",student.section.get(i))
                                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if(task.isSuccessful()){

                                            Toasty.success(getApplicationContext(),)






                                        }
                                    }
                                });
                            }








                        }
                    }
                });*/
        sectionName = new ArrayList<>();

        db.collection("section").whereArrayContains("studentUID",student.StudentID)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot q: Objects.requireNonNull(task.getResult())){
                        sectionName.add(q.toObject(section.class));
                    }
                    spinner1 = findViewById(R.id.spnCourses);
                    ArrayList<CharSequence> g = new ArrayList<>();
                    int i = 0;
                    for (section c: sectionName) {
                        g.add(c.CourseID);
                    }
                    ArrayAdapter<CharSequence> adaptSpin = new ArrayAdapter<>(sendRequestAct.this, android.R.layout.simple_spinner_item, g);
                    spinner1.setAdapter(adaptSpin);
                    spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, final View view,  int position, long id) {
                            pos = position;

                            db.collection("teacher").document(sectionName.get(position).teacherUID).get()
                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @SuppressLint("SetTextI18n")
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            DocumentSnapshot d = task.getResult();
                                            t = new Teacher();

                                            t = Objects.requireNonNull(d).toObject(Teacher.class);
                                            teachername = findViewById(R.id.txtTeachername);
                                            teachername.setText(t.name + " "+t.lastName);

                                            String timeAvailable ,sun1, mon2 , tues3 , wed4,thus5;
                                            if(t.timeAvailable.get(0)!= -1)
                                                sun1= "الاحد: "+ t.timeAvailable.get(0)+" الى "+t.timeAvailable.get(1);
                                            else
                                                sun1="الاحد:غير متاح";
                                            if(t.timeAvailable.get(2)!= -1)
                                                mon2= "الاثنين: "+ t.timeAvailable.get(2)+" الى "+t.timeAvailable.get(3);
                                            else
                                                mon2="الاثنين:غير متاح";
                                            if(t.timeAvailable.get(4)!= -1)
                                                tues3= "الثلاثاء: "+ t.timeAvailable.get(4)+" الى "+t.timeAvailable.get(5);
                                            else
                                                tues3="الثلاثاء:غير متاح";
                                            if(t.timeAvailable.get(6)!= -1)
                                                wed4= "الاربعاء: "+ t.timeAvailable.get(6)+" الى "+t.timeAvailable.get(7);
                                            else
                                                wed4="الاربعاء:غير متاح";
                                            if(t.timeAvailable.get(8)!= -1)
                                                thus5= "الخميس: "+ t.timeAvailable.get(8)+" الى "+t.timeAvailable.get(9);
                                            else
                                                thus5="الخميس:غير متاح";



                                            timeAvailable =sun1 + "\n" + mon2 +"\n"+tues3+"\n"+wed4+"\n"+thus5+"\n";
                                            TextView t1 = (TextView) findViewById(R.id.checktimetxt);
                                            t1.setText(timeAvailable);




                                        }
                                    });


                            Toast.makeText(parent.getContext(),parent.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                else
                {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }

            }
        });

   /*     courseName= new ArrayList<>();
        CollectionReference courseref = db.collection("course");
        courseref.whereArrayContains("studentUID",student.StudentID)
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
                            spinner1 = findViewById(R.id.spnCourses);
                            ArrayList<CharSequence> g = new ArrayList<>();
                            int i = 0;
                            for (Course c: courseName) {
                                g.add(c.courseName);
                            }
                            ArrayAdapter<CharSequence> adaptSpin = new ArrayAdapter<>(sendRequestAct.this, android.R.layout.simple_spinner_item, g);
                            spinner1.setAdapter(adaptSpin);
                            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, final View view,  int position, long id) {
                                    pos = position;

                                    db.collection("teacher").document(courseName.get(position).teacherUID).get()
                                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                    DocumentSnapshot d = task.getResult();
                                                    t = new Teacher();

                                                    t = d.toObject(Teacher.class);
                                                    teachername = findViewById(R.id.txtTeachername);
                                                    teachername.setText(t.name + " "+t.lastName);

                                                    String timeAvailable ,sun1, mon2 , tues3 , wed4,thus5;
                                                    if(t.timeAvailable.get(0)!= -1)
                                                        sun1= "الاحد: "+ t.timeAvailable.get(0)+" الى "+t.timeAvailable.get(1);
                                                    else
                                                        sun1="الاحد:غير متاح";
                                                    if(t.timeAvailable.get(2)!= -1)
                                                        mon2= "الاثنين: "+ t.timeAvailable.get(2)+" الى "+t.timeAvailable.get(3);
                                                    else
                                                        mon2="الاثنين:غير متاح";
                                                    if(t.timeAvailable.get(4)!= -1)
                                                        tues3= "الثلاثاء: "+ t.timeAvailable.get(4)+" الى "+t.timeAvailable.get(5);
                                                    else
                                                        tues3="الثلاثاء:غير متاح";
                                                    if(t.timeAvailable.get(6)!= -1)
                                                        wed4= "الاربعاء: "+ t.timeAvailable.get(6)+" الى "+t.timeAvailable.get(7);
                                                    else
                                                        wed4="الاربعاء:غير متاح";
                                                    if(t.timeAvailable.get(8)!= -1)
                                                        thus5= "الخميس: "+ t.timeAvailable.get(8)+" الى "+t.timeAvailable.get(9);
                                                    else
                                                        thus5="الخميس:غير متاح";



                                                    timeAvailable =sun1 + "\n" + mon2 +"\n"+tues3+"\n"+wed4+"\n"+thus5+"\n";
                                                    TextView t1 = (TextView) findViewById(R.id.checktimetxt);
                                                    t1.setText(timeAvailable);




                                                }
                                            });


                                    Toast.makeText(parent.getContext(),parent.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }

                });*/

        dateText = findViewById(R.id.txtselectdate);

        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        timeText = findViewById(R.id.timespicker);

        timeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                t1 = new TimePickerDialog(sendRequestAct.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        time = hourOfDay+":"+minutes;
                        timeText.setText(time);
                        if(dayOfWeek == 3){
                            System.out.println("SUNDAY"+" Teacher is Available from "+t.timeAvailable.get(0)+" to "+t.timeAvailable.get(1));
                            if(hourOfDay<t.timeAvailable.get(0)||hourOfDay>t.timeAvailable.get(1)){
                                timeLeget= false;

                            }else{timeLeget= true;}
                            System.out.println(timeLeget);

                        }else if(dayOfWeek == 4){
                            System.out.println("MONDAY"+" Teacher is Available from "+t.timeAvailable.get(2)+" to "+t.timeAvailable.get(3));
                            if(hourOfDay<t.timeAvailable.get(2)||hourOfDay>t.timeAvailable.get(3)){
                                timeLeget= false;

                            }else{timeLeget= true;}
                            System.out.println(timeLeget);

                        }else if(dayOfWeek == 5){
                            System.out.println("TUESDAY"+" Teacher is Available from "+t.timeAvailable.get(4)+" to "+t.timeAvailable.get(5));
                            if(hourOfDay<t.timeAvailable.get(4)||hourOfDay>t.timeAvailable.get(5)){
                                timeLeget= false;

                            }else{timeLeget= true;}
                            System.out.println(timeLeget);

                        }else if(dayOfWeek == 6){
                            System.out.println("WEDENSDAY"+" Teacher is Available from "+t.timeAvailable.get(6)+" to "+t.timeAvailable.get(7));
                            if(hourOfDay<t.timeAvailable.get(6)||hourOfDay>t.timeAvailable.get(7)){
                                timeLeget= false;

                            }else{timeLeget= true;}
                            System.out.println(timeLeget);

                        }else if(dayOfWeek == 7){
                            System.out.println("THURSDAY"+" Teacher is Available from "+t.timeAvailable.get(8)+" to "+t.timeAvailable.get(9));
                            if(hourOfDay<t.timeAvailable.get(8)||hourOfDay>t.timeAvailable.get(9)){
                                timeLeget= false;

                            }else{timeLeget= true;}
                            System.out.println(timeLeget);

                        }
                        //if(hourOfDay<teachers.get(pos).timeAvailable.get(dayyy))


                    }
                }, Calendar.getInstance().get(Calendar.HOUR), Calendar.getInstance().get(Calendar.MINUTE), true);
                t1.show();

            }
        });






    }

    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH) ,
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, final int month, int dayOfMonth) {

        int i = month + 1;
        date = dayOfMonth+ "/" + i + "/" + year;
        dateText.setText(date);
        GregorianCalendar GregorianCalendar = new GregorianCalendar(year, month +1, dayOfMonth-1);

         dayOfWeek =GregorianCalendar.get(Calendar.DAY_OF_WEEK);


        ///if the currently selected date is old set dateLeget to false true otherwise
        dateLeget= true;
        if(Calendar.getInstance().get(Calendar.YEAR)>year){
            dateLeget = false;
        }else if(Calendar.getInstance().get(Calendar.YEAR)==year&&Calendar.getInstance().get(Calendar.MONTH)>month){
            dateLeget = false;
        }else if(Calendar.getInstance().get(Calendar.YEAR)==year&&Calendar.getInstance().get(Calendar.MONTH)==month&&Calendar.getInstance().get(Calendar.DAY_OF_MONTH)>dayOfMonth){
            dateLeget = false;
        }

        if(dayOfWeek == 1|| dayOfWeek == 2){
            dateLeget = false;

        }


        System.out.println(dateLeget+" "+dayOfWeek);



    }


    //***********************************************here
    public String createUniqueReqId(){
        Random random = new Random();
        @SuppressLint("DefaultLocale") String idR = String.format("%04d", random.nextInt(10000));
        return student.StudentID+sectionName.get(pos).CourseID+idR;
    }
public void checkRequest(final View view){


        if(txtproblem.getVisibility()==View.VISIBLE){
            reason = txtproblem.getText().toString();
        }





             if(timeLeget&&dateLeget){

                    Map<String, Object> docData = new HashMap<>();
                    docData.put("CourseID", sectionName.get(pos).CourseID);
                    docData.put("Date",date);
                    docData.put("StudentID",student.StudentID);
                    docData.put("TeacherID",sectionName.get(pos).teacherUID);

                    // we need unique req id for each student
                    docData.put("reqID",createUniqueReqId());
                    docData.put("status","0");
                    docData.put("Time",time);
                    docData.put("problem",reason);

                    String id = db.collection("requests").document().getId();
                    //r = new request(txtstudet.toString(),courses.get(pos).teacherUID,timeText.toString(),"0",courses.get(pos).courseID,"2223",dateText.toString());
                    db.collection("request").document(id)
                            .set(docData)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "DocumentSnapshot successfully written!");
                                    Toast.makeText(sendRequestAct.this,"تم ارسال الطلب بنجاح",Toast.LENGTH_LONG).show();
                                    dateText.setText("حدد اليوم");
                                    timeText.setText("حدد الوقت");
                                    reasonSpinner.clearFocus();


                                    new CountDownTimer(7000, 1000) {

                                        public void onTick(long millisUntilFinished) {
                                            view.setEnabled(false);
                                            view.setBackgroundColor(Color.parseColor("#b3b3cc"));

                                        }

                                        public void onFinish() {
                                            view.setEnabled(true);
                                            view.setBackgroundColor(getResources().getColor(R.color.md_blue_700));


                                        }
                                    }.start();

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error writing document", e);
                                }
                            });


                }
                else
                {
                   Toasty.error(view.getContext(),"الرجاء التأكد من الوقت او التاريخ").show();
                }
                


    }

/*    public void checkTimeAvailablity(View v){


               String timeAvailable ,sun1, mon2 , tues3 , wed4,thus5;
        if(teachers.get(pos).timeAvailable.get(0)!= -1)
            sun1= "Sunday: "+ teachers.get(pos).timeAvailable.get(0)+" to "+teachers.get(pos).timeAvailable.get(1);
        else
            sun1="Sunday: unavailable";
        if(teachers.get(pos).timeAvailable.get(2)!= -1)
            mon2= "Monday: "+ teachers.get(pos).timeAvailable.get(2)+" to "+teachers.get(pos).timeAvailable.get(3);
        else
            mon2="Monday: unavailable";
        if(teachers.get(pos).timeAvailable.get(4)!= -1)
            tues3= "Tuesday: "+ teachers.get(pos).timeAvailable.get(4)+" to "+teachers.get(pos).timeAvailable.get(5);
        else
            tues3="Tuesday: unavailable";
        if(teachers.get(pos).timeAvailable.get(6)!= -1)
            wed4= "Wednesday: "+ teachers.get(pos).timeAvailable.get(6)+" to "+teachers.get(pos).timeAvailable.get(7);
        else
            wed4="Wednesday: unavailable";
        if(teachers.get(pos).timeAvailable.get(8)!= -1)
            thus5= "Thursday: "+ teachers.get(pos).timeAvailable.get(8)+" to "+teachers.get(pos).timeAvailable.get(9);
        else
            thus5="Wednesday: unavailable";



        timeAvailable =sun1 + "\n" + mon2 +"\n"+tues3+"\n"+wed4+"\n"+thus5+"\n";
        TextView t = (TextView) findViewById(R.id.checktimetxt);
        t.setText(timeAvailable);

                Toast.makeText(sendRequestAct.this,timeAvailable,Toast.LENGTH_LONG).show();



    }*/



}





