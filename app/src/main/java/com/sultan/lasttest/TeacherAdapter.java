package com.sultan.lasttest;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.MyViewHolder> implements Filterable {
    private List<request> mDataset;
    private List<request> fullmDataset;
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
        fullmDataset = new ArrayList<>(myDataset);


    }

    // Create new views (invoked by the layout manager)
    @Override
    public TeacherAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.requests_status, parent, false);
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
            status ="قيد الانتظار";
        else if (mDataset.get(position).status.equals("2"))
            status ="تم الرفض";
        else if(mDataset.get(position).status.equals("3"))
            status ="ماضي";
        else
            status="تمت الموافقه";





        holder.studentID.setText(mDataset.get(position).StudentID);

        holder.reqStatus.setText(status);
        holder.reqdate.setText(mDataset.get(position).Date);


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
    public Filter getFilter(){
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<request> filterredlist = new ArrayList<>();
            if(constraint == null || constraint.length() == 0) {
                filterredlist.addAll(fullmDataset);
            }else{
                String fliterpattern = constraint.toString().toLowerCase().trim();
                for(request r : fullmDataset){
                    if(r.StudentID.toLowerCase().contains(fliterpattern)){
                        filterredlist.add(r);
                    }
                }
            }
            FilterResults result = new FilterResults();
            result.values =filterredlist;
            return result;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            mDataset.clear();
            mDataset.addAll((List)results.values);
            notifyDataSetChanged();

        }
    };
}