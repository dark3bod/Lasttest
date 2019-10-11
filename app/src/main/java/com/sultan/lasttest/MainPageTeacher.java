package com.sultan.lasttest;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class MainPageTeacher extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView name,course1,course2 , txtdate;
    public final String TAG = "MainPageTeacher";
    public static ArrayList<Course> courses ;
    public static String a,b,c;
    public int i,z ;
    CardView mCard;
    public static ArrayList<request>requests;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_teacher);
        courses = new ArrayList<>();
        txtdate=(TextView)findViewById(R.id.txtmydayTecher) ;


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Coming soon!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        mCard = (CardView) findViewById(R.id.cardView2);
        mCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),officeHoursAct.class);
                startActivity(intent);


            }
        });

        name = (TextView) findViewById(R.id.name);

        setSupportActionBar(toolbar);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            // User is logged in
        }
        Intent intent = getIntent();
        Teacher teacher = (Teacher)intent.getSerializableExtra("teacher");
        name.setText(teacher.name+" "+teacher.lastName);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef;
        i = 0;
        for( i = 0;i<teacher.course.size();i++) {
            docRef = db.collection("course").document(teacher.course.get(i));
            docRef.get().addOnCompleteListener(MainPageTeacher.this, new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            System.out.println(document.get("courseCode").toString()+" "+ document.get("courseName").toString()+" "+document.get("teacherUID").toString()+" "+document.get("studentUID").toString());
                            courses.add(document.toObject(Course.class));
                        } else {
                            ///document doesnt exist
                            Log.d(TAG, "No such Course");

                        }
                    } else {
                        ///task is not succsesful
                        Log.d(TAG, "get Course failed with ", task.getException());

                    }
                }

            });
        }
        //System.out.println(courses.get(0).courseName);
        //course1.setText(courses.get(0).courseCode);
        //course2.setText(courses.get(1).courseCode);




        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);







        ///////////for geting requests to array
        requests = new ArrayList<>();

        final FirebaseFirestore dd = FirebaseFirestore.getInstance();

        dd.collection("request")
                .whereEqualTo("TeacherID", auth.getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                 /*String CourseID = (String)document.get("CourseID"), reqID = (String)document.get("reqID"),StudentID =(String)document.get("StudentID"), TeacherID=(String)document.get("TeacherID"),Time=(String)document.get("Time"), status=(String)document.get("status"), Date=(String)document.get("Date"), problem = (String) document.get("problem");
                                 request r = new request(StudentID,TeacherID,Time,status,CourseID,reqID,Date,problem);*/
                                 if(document.get("status").toString().equals("0")) {
                                     request r = new request();
                                 r.reqID=document.get("reqID").toString();
                                 r.CourseID=document.get("CourseID").toString();
                                 r.Date=document.get("Date").toString();
                                 r.problem = document.get("problem").toString();
                                 r.StudentID=document.get("StudentID").toString();
                                 r.status=document.get("status").toString();
                                 r.TeacherID=document.get("TeacherID").toString();
                                 r.Time=document.get("Time").toString();
                                 r.ID=document.getId();
                                requests.add(r);
                                 }
                                try {
                                    Date s = new SimpleDateFormat("dd/MM/yyyy").parse(document.get("Date").toString());

                                    if (s.after(new Date())){

                                        String x ="dd/MM/yyyy";
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(x);
                                        String date = simpleDateFormat.format(s);
                                        txtdate.setText("Date: " +date + "      " + "Time: "+document.get("Time").toString()+"\n"+"StudentID: "+document.get("StudentID").toString());
                                        txtdate.setTextColor(Color.parseColor("#002038"));
                                        txtdate.setTextSize(20);

                                    }
                                    String status = document.get("status").toString();
                                    if(s.before(new Date()) && status.equals("0")){
                                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                                        dd.collection("request").document(document.getId()).update("status","3");
                                    }else if (s.before(new Date()) && status.equals("2")){
                                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                                        dd.collection("request").document(document.getId()).update("status","4");

                                    }



                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }




                            }



                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });




    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page_teacher, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id == R.id.btn_log_out){
            FirebaseAuth auth = FirebaseAuth.getInstance();
            if (auth.getCurrentUser() != null) {
                // User is logged in
            }
            auth.signOut();

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user == null) {
                // user auth state is changed - user is null
                // launch login activity
                startActivity(new Intent(MainPageTeacher.this, LogIn.class));
                finish();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void saveCourse(){

    }
    public void openRecentRequstsAct(View view){



        //open requests activity with sending the requests that cames from database
            Intent intent = new Intent(MainPageTeacher.this,actReceivedRequest.class);
            intent.putExtra("r",requests);
            startActivity(intent);

            requests = new ArrayList<>();



    }


}
