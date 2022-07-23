package com.example.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Add_Notice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);

        Intent i = getIntent();
        Bundle b = i.getBundleExtra("name");
        String getCourse_name = b.getString("value");

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_notice);
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

        // reference for notice
        EditText et = (EditText) findViewById(R.id.editTextTextMultiLine);
        EditText et_topicName = (EditText) findViewById(R.id.edittext_notice_topic_name);

        // Save and create Button
        Button btn = (Button) findViewById(R.id.save_and_create);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic_name = et_topicName.getText().toString();
                String notice = et.getText().toString();

                Map<String,Object> map=new HashMap<>();
                map.put("Topic_name",topic_name);
                map.put("notice",notice);

                FirebaseDatabase.getInstance().getReference().child("Notice").child(getCourse_name).push()
                        .setValue(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                et.setText("");
                                et_topicName.setText("");
                                Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e)
                            {
                                Toast.makeText(getApplicationContext(),"Could not insert",Toast.LENGTH_LONG).show();
                            }
                        });

            }
        });

        Button btn_view_notice = (Button) findViewById(R.id.view_notice);
        btn_view_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("value", getCourse_name);
                Intent in = new Intent(getApplicationContext(), View_Notice.class);
                in.putExtra("name", bundle);
                startActivity(in);
            }
        });


    }
}