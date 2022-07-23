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

public class Student_View_Notice_Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    Notice_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_notice);


        Intent i = getIntent();
        Bundle b = i.getBundleExtra("name");
        String getCourse_name = b.getString("value");

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_View_notice);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("value", getCourse_name);
                Intent in = new Intent(getApplicationContext(), Add_Notice.class);
                in.putExtra("name", bundle);
                startActivity(in);
            }
        });

        Button btn = (Button) findViewById(R.id.Create_Notice);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("value", getCourse_name);
                Intent in = new Intent(getApplicationContext(), Add_Notice.class);
                in.putExtra("name", bundle);
                startActivity(in);
            }
        });


        recyclerView = findViewById(R.id.recyclerView_notice);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Notice_Model> options =
                new FirebaseRecyclerOptions.Builder<Notice_Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Notice").child(getCourse_name), Notice_Model.class)
                        .build();

        adapter = new Notice_Adapter(options);
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