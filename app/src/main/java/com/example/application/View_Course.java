package com.example.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class View_Course extends AppCompatActivity {

    Toolbar toolbar;

    DatabaseReference rootRef,courseRef;
    RecyclerView recyclerView;
    ArrayList<Course> list;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_course);

        Intent i = getIntent();
        Bundle b = i.getBundleExtra("name");
        String profile_userName = b.getString("value");

        int index = profile_userName.indexOf('@');
        String value = profile_userName.substring(0,index);

        //Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("value",profile_userName);
                Toast.makeText(getApplicationContext(), "Settings clicked", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(getApplicationContext(),DashBoard_Activity.class);
                in.putExtra("name",bundle);
                startActivity(in);
            }
        });

        // Create Course Button
        Button btn = (Button) findViewById(R.id.create_course);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("value",profile_userName);
                Toast.makeText(getApplicationContext(), "Settings clicked", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(getApplicationContext(),Add_Course1.class);
                in.putExtra("name",bundle);
                startActivity(in);
            }
        });

        //RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        // getting firebase root reference
        rootRef = FirebaseDatabase.getInstance().getReference();

        // getting firebase course reference
        courseRef = rootRef.child("Course").child("Teacher").child(value);

        courseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<Course>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Course model = dataSnapshot.getValue(Course.class);
                    list.add(model);
                }
                adapter = new MyAdapter(list,View_Course.this);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(View_Course.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
}