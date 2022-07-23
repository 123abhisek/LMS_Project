package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Student_View_course extends AppCompatActivity {

    FirebaseAuth mAuth;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_course);

        mAuth =  FirebaseAuth.getInstance();
        //TextView tv = (TextView) findViewById(R.id.tv_name);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser mFirebaseuser = mAuth.getCurrentUser();
        if (mFirebaseuser != null){
            // there is some user
            tv = (TextView)findViewById(R.id.tv_name);
            tv.setText(mFirebaseuser.getEmail());
            Toast.makeText(this, "name is : "+mFirebaseuser.getEmail() +" /", Toast.LENGTH_SHORT).show();

        }else {
            // no one logout
            startActivity(new Intent(getApplicationContext(),loginActivity.class));
            finish();
        }

    }

}