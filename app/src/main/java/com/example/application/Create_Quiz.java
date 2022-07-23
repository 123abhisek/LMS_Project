package com.example.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Create_Quiz extends AppCompatActivity {

    String topic,description,test_duration,no_of_question,correct_answer,incorrect_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        Intent i = getIntent();
        Bundle b = i.getBundleExtra("name");
        String getCourse_name = b.getString("value");

        TextInputEditText editText_topic = (TextInputEditText) findViewById(R.id.edit_text_topic);
        TextInputEditText editText_description = (TextInputEditText) findViewById(R.id.edit_text_description);
        TextInputEditText editText_test_duration = (TextInputEditText) findViewById(R.id.edit_text_test_duration);
        TextInputEditText editText_no_of_question = (TextInputEditText) findViewById(R.id.edit_text_marks);
        TextInputEditText editText_correct_answer= (TextInputEditText) findViewById(R.id.edit_text_correct);
        TextInputEditText editText_incorrect_answer = (TextInputEditText) findViewById(R.id.edit_text_incorrect);


        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_quiz);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
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


        Button save_btn = (Button) findViewById(R.id.save_and_create);
        Button view_quiz = (Button) findViewById(R.id.View_quiz);


        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (topic.isEmpty() || description.isEmpty() || test_duration.isEmpty() || no_of_question.isEmpty() || correct_answer.isEmpty() || incorrect_answer.isEmpty() ){
                    Toast.makeText(getApplicationContext(), "Text should not be empty ! ", Toast.LENGTH_SHORT).show();
                }else {

                 */
                topic = editText_topic.getText().toString();
                description = editText_description.getText().toString();
                test_duration = editText_test_duration.getText().toString();
                no_of_question = editText_no_of_question.getText().toString();
                correct_answer = editText_correct_answer.getText().toString();
                incorrect_answer = editText_incorrect_answer.getText().toString();

                    // creating a mapping of data

                    Map<String, Object> map = new HashMap<>();
                    map.put("topic_name", topic);
                    map.put("description", description);
                    map.put("test_duration", test_duration);
                    map.put("no_of_question", no_of_question);
                    map.put("correct_answer", correct_answer);
                    map.put("incorrect_answer", incorrect_answer);

                    // add data to into firebase

                    FirebaseDatabase.getInstance().getReference().child("Quiz").child(getCourse_name).push()
                            .setValue(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    editText_topic.setText("");
                                    editText_description.setText("");
                                    editText_test_duration.setText("");
                                    editText_no_of_question.setText("");
                                    editText_correct_answer.setText("");
                                    editText_incorrect_answer.setText("");
                                    Toast.makeText(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_LONG).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Could not insert", Toast.LENGTH_LONG).show();
                                }
                            });
               // }
            }
        });

        // View Quiz button

        view_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("value",getCourse_name);
                Intent in = new Intent(getApplicationContext(),View_Quiz.class);
                in.putExtra("name",bundle);
                startActivity(in);

                Toast.makeText(Create_Quiz.this, "view Quiz button clicked", Toast.LENGTH_SHORT).show();
            }
        });



    }
}


/*

<com.google.android.material.button.MaterialButton
                android:id="@+id/attachment_assignment"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                style="@style/Widget.MaterialComponents.Button.OutlinedButton"
                android:backgroundTint="@android:color/transparent"
                android:text="Add Attachment"
                android:textColor="@android:color/holo_green_dark"
                android:layout_marginLeft="20dp"
                app:cornerRadius="8dp"
                app:rippleColor="#33AAAAAA"
                app:strokeColor="@android:color/holo_green_dark"
                app:strokeWidth="2dp"
                android:drawableLeft="@drawable/ic_baseline_attach_file_24"
                />

 */
