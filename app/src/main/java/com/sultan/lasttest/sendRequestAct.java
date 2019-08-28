package com.sultan.lasttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import io.opencensus.common.Timestamp;

public class sendRequestAct extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    public static final String TAG = "sendRequestAct";
    private TextView dateText;
    Button s;
    List<Teacher> teachers = new ArrayList<>();
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        s =(Button)findViewById(R.id.btnSend) ;
        setContentView(R.layout.activity_send_request);
        final List<Course> courses = (List<Course>)getIntent().getSerializableExtra("g");

        ArrayList<CharSequence> g = new ArrayList<>();
        int i = 0;
        for (Course c: courses
        ) {
            g.add(c.courseName);
        }
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef;
        i = 0 ;

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
        }
        Spinner spinner1 = findViewById(R.id.spnCourses);
        ArrayAdapter<CharSequence> adaptSpin = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, g);
        spinner1.setAdapter(adaptSpin);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(),courses.get(position).courseID,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dateText = findViewById(R.id.txtselectdate);

        findViewById(R.id.txtselectdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });


    }

    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth+ "/" + month + "/" + year;
        dateText.setText(date);
    }


}
