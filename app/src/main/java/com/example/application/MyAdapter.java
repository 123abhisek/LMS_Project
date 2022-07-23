package com.example.application;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends  RecyclerView.Adapter<MyAdapter.myViewHolder> {

    Bundle bundle;

    ArrayList<Course> dataList;
    Context context;

    public MyAdapter(ArrayList<Course> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_rv_irem,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        bundle = new Bundle();
        String value = dataList.get(position).getCourse_name();

        holder.textViewCourseName.setText(dataList.get(position).getCourse_name());
        holder.textViewCourseName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bundle.putString("value",value);

                Intent in = new Intent(v.getContext(),Course_function.class);
                //Intent in = new Intent(v.getContext(),Add_Course_Module.class);
                in.putExtra("name",bundle);

                v.getContext().startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView textViewCourseName;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCourseName = (TextView) itemView.findViewById(R.id.tv_name_card);
        }
    }

}
