package com.sultan.lasttest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.system.Os.close;

public class MyRequestesAdapter extends RecyclerView.Adapter<MyRequestesAdapter.MyViewHolder> {
    private List<request> mDataset;
    public final String TAG = "MyRequestesAdapter";
    EditText reason;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView textView;
        public TextView studentID ,time , date;
        public Button review , accept , reject;
        public MyViewHolder(CardView v,TextView s,TextView t , TextView d , Button r ,Button a,Button re ) {
            super(v);

            this.studentID = s;
            this.date = d;
            this.time=t;
            textView = v;
            this.review = r;
            this.accept =a;
            this.reject=re;

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
        Button accept = (Button)v.findViewById(R.id.buttonaccpet) ;
        Button reject = (Button)v.findViewById(R.id.buttonreject) ;
        Button review = (Button)v.findViewById(R.id.buttonreview) ;

        MyViewHolder vh = new MyViewHolder(v,studentid,time,date,review,accept,reject);


        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element


        FirebaseFirestore dd = FirebaseFirestore.getInstance();
        final DocumentReference updateRequest = dd.collection("request").document(mDataset.get(position).ID);



        holder.studentID.setText("Student ID: "+mDataset.get(position).StudentID);
        holder.time.setText("Time: "+mDataset.get(position).Time);
        holder.date.setText("Date: "+mDataset.get(position).Date);

        holder.review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////making toast for student prolem

            }
        });
        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                ////rejected request by put in status atrbute #2
                final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("...");
                builder.setMessage("Enter the reason");
                reason = new EditText(view.getContext());
                builder.setView(reason);
                builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Map<String, Object> docData = new HashMap<>();
                        String txt  = reason.getText().toString();
                        updateRequest.update("status","2");
                        docData.put("reason",txt);
                        updateRequest.set(docData,SetOptions.merge());
                        Toast.makeText(view.getContext(),"Rejected request",Toast.LENGTH_LONG).show();
                        mDataset.remove(position);
                        notifyItemRemoved(position);
                        notifyItemChanged(position);

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();



            }
        });
        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                ////accpted request by put in status atrbute #1
                final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("...");
                builder.setMessage("Are you Sure?");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        updateRequest.update("status","1");
                        mDataset.remove(position);
                        notifyItemRemoved(position);
                        notifyItemChanged(position);
                        Toast.makeText(view.getContext(), "Accepted request", Toast.LENGTH_SHORT).show();

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}