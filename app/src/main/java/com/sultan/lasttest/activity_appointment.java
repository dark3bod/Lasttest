package com.sultan.lasttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

public class activity_appointment extends AppCompatActivity {

    CalendarView cv;
    TextView txtdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
       txtdata =(TextView)findViewById(R.id.txtdate);

        cv = (CalendarView)findViewById(R.id.cv);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                int x = i1 + 1;
                String Date = i + "/"+x+"/"+i2;
                txtdata.setText(Date);
                //change the textview when calender changed

            }
        });
    }

}
