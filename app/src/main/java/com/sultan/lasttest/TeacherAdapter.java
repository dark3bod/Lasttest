package com.sultan.lasttest;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.MyViewHolder> {
    private List<request> mDataset;
    public final String TAG = "TeacherAdapter";
    String  status;





    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case


        public CardView textView;
        public TextView studentID , reqdate , reqStatus;
        public MyViewHolder(CardView v,TextView studentID , TextView reqdate , TextView reqStatus) {
            super(v);
            textView = v;
            this.studentID = studentID;
            this.reqdate =reqdate;
            this.reqStatus=reqStatus;


        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public TeacherAdapter(List<request> myDataset ) {

        mDataset = myDataset;


    }

    // Create new views (invoked by the layout manager)
    @Override
    public TeacherAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.requesys, parent, false);
        //TextView corseinfo = (TextView) findViewById(R.id._course_info);
        TextView studentID = (TextView) v.findViewById(R.id.requestcousename);
        TextView reqdate = (TextView) v.findViewById(R.id.requestdate);
        TextView reqstatus = (TextView) v.findViewById(R.id.requeststatus);
        MyViewHolder vh = new MyViewHolder(v,studentID,reqdate,reqstatus);


        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element


        if(mDataset.get(position).status.equals("0"))
            status ="Pending";
        else if (mDataset.get(position).status.equals("2"))
            status ="Canceled";
        else if(mDataset.get(position).status.equals("3"))
            status ="Expire";
        else
            status="Confirmed";





        holder.studentID.setText(mDataset.get(position).StudentID);

        holder.reqStatus.setText(status);
        holder.reqdate.setText(mDataset.get(position).Date);


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}