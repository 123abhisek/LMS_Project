package com.example.application;

import android.graphics.Color;
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

public class Quiz_adapter extends FirebaseRecyclerAdapter<Quiz_model,Quiz_adapter.myViewHolder> {

    public Quiz_adapter(@NonNull FirebaseRecyclerOptions<Quiz_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Quiz_adapter.myViewHolder holder, int position, @NonNull Quiz_model model) {
        holder.topic_name.setText(model.getTopic_name());
        holder.description.setText(model.getDescription());
    }

    @NonNull
    @Override
    public Quiz_adapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_layout_rv,parent,false);
        return new Quiz_adapter.myViewHolder(view);
    }



    class myViewHolder extends RecyclerView.ViewHolder{

        TextView topic_name,description;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            topic_name=(TextView)itemView.findViewById(R.id.nametext);
            description=(TextView)itemView.findViewById(R.id.coursetext);

        }
    }
}
