package com.sultan.lasttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    FirebaseUser user;
    public Student student;
    public Teacher teacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = FirebaseAuth.getInstance().getCurrentUser();
        checkCurrentUser(user);
    }
    private void checkCurrentUser(FirebaseUser user) {
        // [START check_current_user]

        if (user != null) {
            //splash screen is over go to main menu
            FirebaseAuth auth = FirebaseAuth.getInstance();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            String docid = auth.getUid();
            DocumentReference docRef;
            docRef = db.collection("student").document(docid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            student = document.toObject(Student.class);
                            Toast.makeText(getApplicationContext(),student.StudentID, Toast.LENGTH_SHORT).show();
                            ///goto student main page
                            Intent intent = new Intent(getApplicationContext(), MainPage.class);
                            intent.putExtra("student",student);
                            startActivity(intent);
                            finish();
                        }else{
                            FirebaseAuth auth = FirebaseAuth.getInstance();
                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            String docid = auth.getUid();
                            DocumentReference docRef;
                            docRef = db.collection("teacher").document(docid);
                            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        DocumentSnapshot document = task.getResult();
                                        if (document.exists()) {

                                            teacher = document.toObject(Teacher.class);
                                            Toast.makeText(getApplicationContext(),teacher.email, Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), MainPageTeacher.class);
                                            intent.putExtra("teacher",teacher);
                                            startActivity(intent);
                                            finish();
                                        }
                                    } else {
                                        ///task is not succsesful

                                    }
                                }
                            });
                        }
                    } else {
                        ///task is not succsesful
                    }
                }
            });








            /*Intent intent = new Intent(this, MainPage.class);
            startActivity(intent);
            finish();*/




        } else {
            // No user is signed in
            /*View v = findViewById(R.id.activity_main_id);
            Snackbar snackbar = Snackbar
                    .make(v, "logged out due to inactivity", Snackbar.LENGTH_LONG);
            snackbar.show();*/
            //Toast.makeText(getApplicationContext(),"logged out",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LogIn.class);
            startActivity(intent);
            finish();
        }
        // [END check_current_user]
    }


}
