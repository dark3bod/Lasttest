package com.sultan.lasttest;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirestoreRegistrar;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.FirestoreGrpc;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {
    private List<request> mDataset;
    private List<Course> mDataset1;
    public final String TAG = "StudentAdapter";
    String cccc, status;





    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case


        public CardView textView;
        public TextView coursename , reqdate , reqStatus;
        public MyViewHolder(CardView v,TextView coursename , TextView reqdate , TextView reqStatus) {
            super(v);
            textView = v;
            this.coursename = coursename;
            this.reqdate =reqdate;
            this.reqStatus=reqStatus;


        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public StudentAdapter(List<request> myDataset ,List<Course> mDataset1 ) {
        this.mDataset1 = mDataset1;
        mDataset = myDataset;


    }

    // Create new views (invoked by the layout manager)
    @Override
    public StudentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.requesys, parent, false);
        //TextView corseinfo = (TextView) findViewById(R.id._course_info);
        TextView reqcourse = (TextView) v.findViewById(R.id.requestcousename);
        TextView reqdate = (TextView) v.findViewById(R.id.requestdate);
        TextView reqstatus = (TextView) v.findViewById(R.id.requeststatus);
        MyViewHolder vh = new MyViewHolder(v,reqcourse,reqdate,reqstatus);


        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element


        if(mDataset.get(position).status.equals("0"))
            status ="Padding";
        else if (mDataset.get(position).status.equals("2"))
            status ="Canceled";
        else
            status ="Confirmed";

      /* if(mDataset.get(position).CourseID.equals("0001"))
            cccc ="Data structers";
        else if (mDataset.get(position).CourseID.equals("0002"))
            cccc ="programing";
        else if (mDataset.get(position).CourseID.equals("0003"))
            cccc ="Data analysis";
       else if (mDataset.get(position).CourseID.equals("0004"))
           cccc ="Database";*/


       for(int i = 0 ; i< mDataset1.size();i++){
           String id = mDataset.get(position).CourseID;
           if(id.equals(mDataset1.get(i).courseID)){
               cccc = mDataset1.get(i).courseName;
           }
       }







        holder.coursename.setText(cccc);
        holder.reqStatus.setText(status);
        holder.reqdate.setText(mDataset.get(position).Date);


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}