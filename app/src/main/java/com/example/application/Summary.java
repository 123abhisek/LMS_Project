
package com.example.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class Summary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Intent i = getIntent();
        Bundle b = i.getBundleExtra("name");
        String getCourse_name = b.getString("value");

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_summary);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("value", getCourse_name);
                Intent in = new Intent(getApplicationContext(), Course_function.class);
                in.putExtra("name", bundle);
                startActivity(in);
            }
        });

        TextView tv_courseName = (TextView) findViewById(R.id.tv_course_name);
        tv_courseName.setText(getCourse_name);

        TextView tv_courseId = (TextView) findViewById(R.id.tv_course_ID);
        Random rand = new Random();
        int code = rand.nextInt();
        tv_courseId.setText(Integer.toString(code));


    }
}