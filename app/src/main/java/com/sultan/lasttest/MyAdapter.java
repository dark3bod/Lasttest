package com.sultan.lasttest;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Course> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView textView;
        public TextView corseinfo,coursename;
        public MyViewHolder(CardView v,TextView c,TextView f) {
            super(v);
            textView = v;
            this.corseinfo = c;
            this.coursename = f;

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Course> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);
        //TextView corseinfo = (TextView) findViewById(R.id._course_info);
        TextView corseinfo = (TextView) v.findViewById(R.id._course_info);
        TextView coursename = (TextView) v.findViewById(R.id.course_code_name);
        MyViewHolder vh = new MyViewHolder(v,corseinfo,coursename);


        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.corseinfo.setText("Course : "+mDataset.get(position).courseName+" Section ;");
        holder.coursename.setText(mDataset.get(position).courseCode);
        //holder.textView.setText(mDataset.get(position).courseName);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}