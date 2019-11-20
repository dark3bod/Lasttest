package com.sultan.lasttest.teacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sultan.lasttest.R;
import com.sultan.lasttest.database.Teacher;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class officeHoursAct extends AppCompatActivity {
    Button btn;
    List<Integer> ofHours;

    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public EditText e0 ,e1,e2,e3,e4,e5,e6,e7,e8,e9 , office , floor,building;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.office_hours_activity);
         e0 =(EditText) findViewById(R.id.txtofficehours0);
         e1 =(EditText) findViewById(R.id.txtofficehours1);
         e2 =(EditText) findViewById(R.id.txtofficehours2);
         e3 =(EditText) findViewById(R.id.txtofficehours3);
         e4 =(EditText) findViewById(R.id.txtofficehours4);
         e5 =(EditText) findViewById(R.id.txtofficehours5);
         e6 =(EditText) findViewById(R.id.txtofficehours6);
         e7 =(EditText) findViewById(R.id.txtofficehours7);
         e8 =(EditText) findViewById(R.id.txtofficehours8);
         e9 =(EditText) findViewById(R.id.txtofficehours9);
         office=(EditText) findViewById(R.id.txtOfficeNo);
         floor=(EditText) findViewById(R.id.txtFloorNo);
         building =(EditText) findViewById(R.id.txtbuildingNo);
       System.out.println(id);
        db.collection("teacher").document(id).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot d= task.getResult();
                        Teacher t1 = new Teacher();
                        t1=d.toObject(Teacher.class);
                         ofHours = t1.timeAvailable;
                        e0.setText(ofHours.get(0).toString());
                        e1.setText(ofHours.get(1).toString());
                        e2.setText(ofHours.get(2).toString());
                        e3.setText(ofHours.get(3).toString());
                        e4.setText(ofHours.get(4).toString());
                        e5.setText(ofHours.get(5).toString());
                        e6.setText(ofHours.get(6).toString());
                        e7.setText(ofHours.get(7).toString());
                        e8.setText(ofHours.get(8).toString());
                        e9.setText(ofHours.get(9).toString());
                        String f1loor = d.get("FloorNO").toString();
                        String of = d.get("OfficeNO").toString();
                        String b1uilding = d.get("BuildingNO").toString();
                        floor.setText(f1loor);
                        building.setText(b1uilding);
                        office.setText(of);






                    }
                });


    }
    public  void saveInfo(View v){
        try {

            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("هل انت متأكد");


            builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DocumentReference documentReference = db.collection("teacher").document(id);
                    Integer[] w = new Integer[ofHours.size()];
                    w[0]=Integer.parseInt(e0.getText().toString());
                    w[1]=Integer.parseInt(e1.getText().toString());
                    w[2]=Integer.parseInt(e2.getText().toString());
                    w[3]=Integer.parseInt(e3.getText().toString());
                    w[4]=Integer.parseInt(e4.getText().toString());
                    w[5]=Integer.parseInt(e5.getText().toString());
                    w[6]=Integer.parseInt(e6.getText().toString());
                    w[7]=Integer.parseInt(e7.getText().toString());
                    w[8]=Integer.parseInt(e8.getText().toString());
                    w[9]=Integer.parseInt(e9.getText().toString());





                    List<Integer> w1= null;
                    w1 = Arrays.asList(w);


                    if((w1.get(0)>24||w1.get(0)<0)||(w1.get(1)>24||w1.get(1)<0)||(w1.get(2)>24||w1.get(2)<0)||
                            (w1.get(3)>24||w1.get(3)<0)||(w1.get(4)>24||w1.get(4)<0)||(w1.get(5)>24||w1.get(5)<0)||
                            (w1.get(6)>24||w1.get(6)<0)||(w1.get(7)>24||w1.get(7)<0)||(w1.get(8)>24||w1.get(8)<0)||
                            (w1.get(9)>24||w1.get(9)<0)

                    ){
                        Toasty.error(getApplicationContext(),"الرجاء التأكد من الوقت").show();

                    }else{   String offic =office.getText().toString();
                        String flr = floor.getText().toString();
                        String build = building.getText().toString();

                        Map<String, Object> updateInfo = new HashMap<>();
                        updateInfo.put("BuildingNO",build);
                        updateInfo.put("FloorNO",flr);
                        updateInfo.put("OfficeNO",offic);
                        updateInfo.put("timeAvailable",Arrays.asList(w1.get(0),w1.get(1),w1.get(2),w1.get(3),w1.get(4),w1.get(5),w1.get(6),w1.get(7),w1.get(8),w1.get(9)));

                        documentReference.update(updateInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful());
                                Toasty.success(getApplicationContext(),"تم حفظ البيانات").show();
                            }
                        });}



                }
            });
            builder.setNegativeButton("لا", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    dialogInterface.dismiss();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();



        }catch (Exception c){
            Toasty.error(getApplicationContext(),  c.getMessage());

        }



    }
}
