package com.example.application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyPdfAdapter extends  RecyclerView.Adapter<MyPdfAdapter.myViewHolder> {

    Bundle bundle;

    ArrayList<uploadPDF> dataList;
    Context context;

    public MyPdfAdapter(ArrayList<uploadPDF> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyPdfAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pdf_rv_item,parent,false);
        return new MyPdfAdapter.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPdfAdapter.myViewHolder holder, int position) {

        holder.textViewCourseName.setText(dataList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView textViewCourseName;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCourseName = (TextView) itemView.findViewById(R.id.pdf_file_name_TV);
        }
    }

}
