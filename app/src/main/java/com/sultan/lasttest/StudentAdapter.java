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

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {
    private List<request> mDataset;
    public final String TAG = "StudentAdapter";
    String status, CourseName;




    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case


        public CardView textView;
        public TextView requests;
        public MyViewHolder(CardView v,TextView c) {
            super(v);
            textView = v;
            this.requests = c;


        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public StudentAdapter(List<request> myDataset) {
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
        TextView reqinfo = (TextView) v.findViewById(R.id.requestinfo);
        MyViewHolder vh = new MyViewHolder(v,reqinfo);


        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element



        if(mDataset.get(position).status.equals("0"))
            status = "Padding";
        else if (mDataset.get(position).status.equals("2"))
            status ="Canceled";
        else
            status="confirmed";







        holder.requests.setText(mDataset.get(position).CourseID+  mDataset.get(position).Date +status);


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}