package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

public class View_single_Assignment_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_assignment);

        Intent i = getIntent();
        Bundle b = i.getBundleExtra("name");
        String getTopic_name = b.getString("Topic_name");
        String get_description = b.getString("Description");
        String get_marks = b.getString("Marks");

        TextView textView1 = (TextView) findViewById(R.id.topic_name_assignment);
        TextView textView2 = (TextView) findViewById(R.id.description_assignment);

        textView1.setText(getTopic_name);
        textView2.setText(get_description);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_single_assignment);
        toolbar.setTitle(getTopic_name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Bundle bundle = new Bundle();
                //bundle.putString("value", getCourse_name);
                Intent in = new Intent(getApplicationContext(), View_Assignment_Activity.class);
                //in.putExtra("name", bundle);
                startActivity(in);
            }
        });

    }
}