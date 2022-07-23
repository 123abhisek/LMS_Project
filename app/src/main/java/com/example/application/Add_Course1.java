package com.example.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_Course1 extends AppCompatActivity {

    Toolbar toolbar;

    //Map<String, Object> Course_name;
    Course model = new Course();

    //Firebase Real Time database
    DatabaseReference root_databaseReference,course_reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course1);

        Intent i = getIntent();
        Bundle b = i.getBundleExtra("name");
        String profile_userName = b.getString("value");

        int index = profile_userName.indexOf('@');
        String value = profile_userName.substring(0,index);

        EditText editText = findViewById(R.id.edittext);
        Button btn = findViewById(R.id.addButton);
        Button view = findViewById(R.id.viewCourse);

        toolbar = findViewById(R.id.toolbar3);
        toolbar.setTitle("");
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
                Intent in = new Intent(getApplicationContext(),View_Course.class);
                in.putExtra("name",bundle);
                startActivity(in);
            }
        });

        Course model = new Course(); // Creating object of Course class

        //On clicking Add Button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editText.getText().toString(); // getting Course Name

                if (name.isEmpty()) {
                    Toast.makeText(Add_Course1.this, "Invalid Input ! ..", Toast.LENGTH_SHORT).show();
                    view.setVisibility(View.INVISIBLE);
                } else {
                    //Firebase

                    //Add data
                    String course_name = model.getCourse_name();

                    root_databaseReference = FirebaseDatabase.getInstance().getReference();
                    course_reference = root_databaseReference.child("Course").child("Teacher").child(value);
                    course_reference.child(name).child("course_name").setValue(name);


                    Toast.makeText(Add_Course1.this, "Course Added Successfully", Toast.LENGTH_SHORT).show();
                    // Button is set visible when data is successfully added
                    view.setVisibility(View.VISIBLE);

                    Bundle bundle = new Bundle();
                    bundle.putString("value",profile_userName);
                    Toast.makeText(getApplicationContext(), "Settings clicked", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(getApplicationContext(),View_Course.class);
                    in.putExtra("name",bundle);
                    startActivity(in);

                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),View_Course.class);
                //Intent in = new Intent(getApplicationContext(),Add_Course_Module.class);
                startActivity(in);
            }
        });


    }

}