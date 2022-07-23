package com.example.application;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Notice_Adapter extends FirebaseRecyclerAdapter<Notice_Model,Notice_Adapter.myViewHolder> {

    public Notice_Adapter(@NonNull FirebaseRecyclerOptions<Notice_Model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Notice_Adapter.myViewHolder holder, int position, @NonNull Notice_Model model) {

        holder.topic_name.setText(model.getTopic_name());
        //holder.notice_name.setText(model.getNotice());

        /*holder.edit_assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Edit button is clicked", Toast.LENGTH_SHORT).show();
            }
        });

        holder.delete_assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Delete button is clicked", Toast.LENGTH_SHORT).show();
            }
        });

         */


    }

    @NonNull
    @Override
    public Notice_Adapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_layout_rv,parent,false);
        return new Notice_Adapter.myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView topic_name;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            topic_name=(TextView)itemView.findViewById(R.id.nametext);

        }
    }
}
