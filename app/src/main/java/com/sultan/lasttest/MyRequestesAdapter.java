package com.sultan.lasttest;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRequestesAdapter extends RecyclerView.Adapter<MyRequestesAdapter.MyViewHolder> {
    private List<request> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView textView;
        public TextView studentID ,time , date;
        public MyViewHolder(CardView v,TextView s,TextView t , TextView d) {
            super(v);

            this.studentID = s;
            this.date = d;
            this.time=t;
            textView = v;

        }


    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyRequestesAdapter(List<request> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyRequestesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.receivedrequest,parent, false);
        //TextView corseinfo = (TextView) findViewById(R.id._course_info);
        TextView studentid = (TextView) v.findViewById(R.id.txtstudntid);
        TextView time = (TextView) v.findViewById(R.id.txttime1 );
        TextView date = (TextView) v.findViewById(R.id.txtdate1);
        MyViewHolder vh = new MyViewHolder(v,studentid,time,date);


        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.studentID.setText("Student ID: "+mDataset.get(position).StudentID);
        holder.time.setText(mDataset.get(position).Time);
        holder.date.setText(mDataset.get(position).Date);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}