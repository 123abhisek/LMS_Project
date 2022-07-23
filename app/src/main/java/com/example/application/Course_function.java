package com.example.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Course_function extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_function);


        Intent i = getIntent();
        Bundle b = i.getBundleExtra("name");
        String getCourse_name = b.getString("value");

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        collapsingToolbarLayout.setTitle(getCourse_name);



        // Card Home Module
        CardView home_cardView = (CardView) findViewById(R.id.Home_module);
        home_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("value",getCourse_name);
                Intent in = new Intent(getApplicationContext(),Add_Course_Module.class);
                in.putExtra("name",bundle);
                startActivity(in);
            }
        });


        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_cols);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                FirebaseUser mFirebaseuser = mAuth.getCurrentUser();
                Bundle bundle = new Bundle();
                bundle.putString("value", mFirebaseuser.getEmail());
                Toast.makeText(getApplicationContext(), "Settings clicked", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(getApplicationContext(),View_Course.class);
                in.putExtra("name",bundle);
                startActivity(in);
            }
        });


        /** Functionality **/

        // Summary
        LinearLayout linearLayout_summary = (LinearLayout) findViewById(R.id.summary);
        linearLayout_summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("value",getCourse_name);
                Intent in = new Intent(getApplicationContext(),Summary.class);
                in.putExtra("name",bundle);
                startActivity(in);
                Toast.makeText(Course_function.this, "Summary clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Create Quiz
        LinearLayout linearLayout_quiz = (LinearLayout) findViewById(R.id.quiz);
        linearLayout_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("value",getCourse_name);
                Intent in = new Intent(getApplicationContext(),View_Quiz.class);
                in.putExtra("name",bundle);
                startActivity(in);
                Toast.makeText(Course_function.this, "Quiz clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Assignment
        LinearLayout linearLayout_assignment = (LinearLayout) findViewById(R.id.assignment);
        linearLayout_assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("value",getCourse_name);
                Intent in = new Intent(getApplicationContext(),View_Assignment_Activity.class);
                in.putExtra("name",bundle);
                startActivity(in);
            }
        });

        // Add Notice
        LinearLayout linearLayout_notice = (LinearLayout) findViewById(R.id.Add_notice);
        linearLayout_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("value",getCourse_name);
                Intent in = new Intent(getApplicationContext(),View_Notice.class);
                in.putExtra("name",bundle);
                startActivity(in);
                Toast.makeText(Course_function.this, "Notice clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Discussion
        LinearLayout linearLayout_discussion = (LinearLayout) findViewById(R.id.Discussions);
        linearLayout_discussion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Course_function.this, "Assignment clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Grade
        LinearLayout linearLayout_grade = (LinearLayout) findViewById(R.id.grades);
        linearLayout_grade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Course_function.this, "Assignment clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Syllabus
        LinearLayout linearLayout_syllabus = (LinearLayout) findViewById(R.id.syllabus);
        linearLayout_syllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Course_function.this, "Assignment clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Modules
        LinearLayout linearLayout_modules = (LinearLayout) findViewById(R.id.modules);
        linearLayout_modules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("value",getCourse_name);
                Intent in = new Intent(getApplicationContext(),Add_Course_Module.class);
                in.putExtra("name",bundle);
                startActivity(in);
            }
        });


    }
}