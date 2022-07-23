package com.example.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Student_View_Assignment_Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    Assignment_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_assignment);


        Intent i = getIntent();
        Bundle b = i.getBundleExtra("name");
        String getCourse_name = b.getString("value");

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_View_assignment);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("value", getCourse_name);
                Intent in = new Intent(getApplicationContext(), Create_Assignment.class);
                in.putExtra("name", bundle);
                startActivity(in);
            }
        });

        // Create Assignment
        Button btn = (Button) findViewById(R.id.Create_Assignment);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("value", getCourse_name);
                Intent in = new Intent(getApplicationContext(), Create_Assignment.class);
                in.putExtra("name", bundle);
                startActivity(in);

            }
        });


        recyclerView = findViewById(R.id.recyclerView_assignment);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Assignment_Model> options =
                new FirebaseRecyclerOptions.Builder<Assignment_Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Assignments").child(getCourse_name), Assignment_Model.class)
                        .build();

        adapter = new Assignment_adapter(options);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}