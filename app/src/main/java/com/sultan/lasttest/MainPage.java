package com.sultan.lasttest;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.Document;
import com.squareup.okhttp.internal.DiskLruCache;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView name,studentId;
    public static ArrayList<Course> courses ;
    Student student;
    public static ArrayList<Course>courseName;
    public final String TAG = "MainPage";
    CardView mCard;



    ArrayList<request> requests;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        name = (TextView) findViewById(R.id.name);
        courses = new ArrayList<>();
        studentId = (TextView) findViewById(R.id.student_id);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            // User is logged in
        }
        Intent intent = getIntent();
        student = (Student)intent.getSerializableExtra("student");
        name.setText(student.name+" "+student.lastName);
        studentId.setText(student.StudentID);

        mCard = (CardView) findViewById(R.id.cardView2);
        mCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Courses.class);

                intent.putExtra("c",courses);
                startActivity(intent);


            }
        });



        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef;
        int i = 0;
        for( i = 0;i<student.course.size();i++) {
            docRef = db.collection("course").document(student.course.get(i));
            docRef.get().addOnCompleteListener(MainPage.this, new OnCompleteListener<DocumentSnapshot>() {
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






        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Coming soon!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);



        requests = new ArrayList<>();


        FirebaseFirestore dd = FirebaseFirestore.getInstance();
        final String[] xxx = new String[1];


        dd.collection("request")
                .whereEqualTo("StudentID",student.StudentID)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());


                                final request r = new request();
                                r.CourseID = document.get("CourseID").toString();
                                r.Date=document.get("Date").toString();
                                r.status=document.get("status").toString();

                                requests.add(r);


                            }



                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        courseName= new ArrayList<>();
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
        getMenuInflater().inflate(R.menu.main_page, menu);
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
                        startActivity(new Intent(MainPage.this, LogIn.class));
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
    public void opencalnder(View v){
        Intent intent = new Intent(MainPage.this,activity_appointment.class);
        startActivity(intent);
    }
    ArrayList<Course>courses1=new ArrayList<>();
    public void openSendAct(View v){
        //Open Send request page

        Intent intent = new Intent(MainPage.this,sendRequestAct.class);
        intent.putExtra("s",student);
        intent.putExtra("g",courses);
        startActivity(intent);



    }


    public int in;
    public void openRecentReqsAct(View v){
        //Open Recent requests page
        System.out.println(courseName.size());

        Intent intent = new Intent(MainPage.this,Actrequests.class);
        intent.putExtra("r",requests);
        intent.putExtra("c",courseName);
        startActivity(intent);
        requests = new ArrayList<>();
        courseName = new ArrayList<>();

    }
}
