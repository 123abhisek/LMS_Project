package com.example.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Create_Assignment extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    DatabaseReference Assign_rootRef;
    DatabaseReference assignmentRef;
    DatabaseReference courseRef;
    Task<Void> unique_AssignmentRef;
    DatabaseReference assignmentValueRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_assignment);

        Intent i = getIntent();
        Bundle b = i.getBundleExtra("name");
        String getCourse_name = b.getString("value");

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_assignment);
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

        // On click Date
        ImageView imageView_date = (ImageView) findViewById(R.id.imageView_date);
        TextView text_date = (TextView) findViewById(R.id.text_date);
        text_date.setText("Wednesday,September 1,1850");
        imageView_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Date picker dialog

                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }
        });

        // On click Time
        ImageView imageView_time = (ImageView) findViewById(R.id.imageView_time);
        TextView text_time = (TextView) findViewById(R.id.text_time);
        text_time.setText("1:8 AM");
        imageView_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Time picker dialog
                DialogFragment dialogFragment = new TimePickerFragment();
                dialogFragment.show(getSupportFragmentManager(),"TimePicker");
            }
        });

        // Save and Create Button
        Button btn = (Button) findViewById(R.id.save_and_create);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Topic
                TextInputEditText editText_topic = (TextInputEditText) findViewById(R.id.edit_text_topic);
                String topic_name = editText_topic.getText().toString();

                // Description
                TextInputEditText editText_description = (TextInputEditText) findViewById(R.id.edit_text_description);
                String description_name = editText_description.getText().toString();

                // Add Marks
                TextInputEditText editText_marks = (TextInputEditText) findViewById(R.id.edit_text_marks);
                String topic_marks = editText_marks.getText().toString();

                if (topic_name.isEmpty() || description_name.isEmpty() || topic_marks.isEmpty()){
                    Toast.makeText(v.getContext(), "Should not be empty ! ", Toast.LENGTH_SHORT).show();
                }else {

                    Map<String,Object> map=new HashMap<>();
                    map.put("topic_name",topic_name);
                    map.put("description",description_name);
                    map.put("marks",topic_marks);

                    FirebaseDatabase.getInstance().getReference().child("Assignments").child(getCourse_name).push()
                            .setValue(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    editText_topic.setText("");
                                    editText_description.setText("");
                                    editText_marks.setText("");
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



                    // Alert box --> saying that successfully created
                    AlertDialog.Builder builder = new AlertDialog.Builder(Create_Assignment.this);
                    View view1 = getLayoutInflater().from(Create_Assignment.this).inflate(R.layout.dialog_box_assignment,null);
                    builder.setView(view1)
                            .setPositiveButton("Add Solution", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(Create_Assignment.this, "Add solution clickedv", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("cancel",null)
                            .setCancelable(true);

                    AlertDialog alert = builder.create();
                    alert.show();
                }


            }
        });

        // View Assignment Button
        Button btn_view_assignment = (Button) findViewById(R.id.View_assignment);
        btn_view_assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputEditText editText_topic = (TextInputEditText) findViewById(R.id.edit_text_topic);
                String topic_name = editText_topic.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("value",getCourse_name);
                Intent in = new Intent(getApplicationContext(),View_Assignment_Activity.class);
                in.putExtra("name",bundle);
                startActivity(in);

                Toast.makeText(Create_Assignment.this, "View assignment clicked", Toast.LENGTH_SHORT).show();

            }
        });


    }

    // For Date Picker dialog
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.HOUR_OF_DAY,dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        TextView text_date = (TextView) findViewById(R.id.text_date);
        text_date.setText(currentDateString);

    }
}