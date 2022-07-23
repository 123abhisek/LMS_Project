package com.example.application;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Assignment_adapter extends FirebaseRecyclerAdapter<Assignment_Model,Assignment_adapter.myViewHolder> {

    public Assignment_adapter(@NonNull FirebaseRecyclerOptions<Assignment_Model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Assignment_Model model) {
        holder.topic_name.setText(model.getTopic_name());
        holder.description.setText(model.getDescription());
        holder.marks.setText(model.getMarks());
        
        holder.edit_assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Edit button is clicked", Toast.LENGTH_SHORT).show();
            }
        });
        
       holder.linearLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {



               Bundle bundle = new Bundle();
               bundle.putString("Topic_name", model.getTopic_name());
               bundle.putString("Description", model.getDescription());
               bundle.putString("Marks", model.getMarks());

               Intent in = new Intent(v.getContext(),View_single_Assignment_Activity.class);
               in.putExtra("name",bundle);
               v.getContext().startActivity(in);
           }
       });
        
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView topic_name,description,marks;
        ImageView edit_assignment ;
        LinearLayout linearLayout;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            topic_name=(TextView)itemView.findViewById(R.id.nametext);
            description=(TextView)itemView.findViewById(R.id.coursetext);
            marks=(TextView)itemView.findViewById(R.id.emailtext);
            
            edit_assignment = (ImageView) itemView.findViewById(R.id.edit_assignment);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.click);


        }
    }

}