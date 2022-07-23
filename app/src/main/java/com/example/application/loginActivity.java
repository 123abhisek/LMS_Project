package com.example.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class loginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private FirebaseAuth auth;
    private EditText inputEmail, inputPassword;
    private ProgressBar progressBar;
    private Button btnSignup, btnLogin;//, btnReset;

    private EditText teach_inputEmail, teach_inputPassword;
    private ProgressBar teach_progressBar;
    private Button teach_btnSignup, teach_btnLogin, teach_btnReset;

    DatabaseReference profile_rootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
//            LOGS USER IN ONCE IT FINDS HE HAD LOGGED IN!
            startActivity(new Intent(loginActivity.this, DashBoard_Activity.class));
            finish();
        }
        setContentView(R.layout.activity_login);



//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        // Student
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        //btnReset = (Button) findViewById(R.id.btn_reset_password);

        //Spinner spinner=(Spinner) findViewById(R.id.spinner);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        /*Spinner spinner=(Spinner) findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(this);


        List<String> choice=new ArrayList<String>();
        choice.add("choose");
        choice.add("Teacher");
        choice.add("Student");

        ArrayAdapter<String> DataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,choice);

        DataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(DataAdapter);

         */


        // Button listener for student

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginActivity.this, SignupActivity.class));
            }
        });

       /* btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginActivity.this, ResetPasswordActivity.class));
            }
        });

        */

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                //final String id = auth.getCurrentUser().getUid();
               // profile_rootRef = FirebaseDatabase.getInstance().getReference("User").child("Teacher");
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        inputPassword.setError("set password correctly");
                                    } else {
                                        Toast.makeText(loginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }

                                } else {
                                    Intent intent = new Intent(loginActivity.this, DashBoard_Activity.class);
                                    startActivity(intent);
                                    finish();

                                    /*String item_item = String.valueOf(spinner.getSelectedItem());
                                    if(item_item.equals("Teacher")){
                                        Toast.makeText(getApplicationContext(), "loginned as " + item_item, Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),DashBoard_Activity.class));
                                        finish();
                                    }else if(item_item.equals("Student")){
                                        Toast.makeText(getApplicationContext(), "loginned as " + item_item, Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),Student_View_course.class));
                                        finish();
                                    }else {
                                        // ****
                                    }

                                     */


                                   /* DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("User").child("Teacher");
                                    root.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                                            String type = snapshot.child("user_Type").getValue().toString();
                                            String username = snapshot.child("email").getValue().toString();

                                            if (email.equals(username) && type.equals("Teacher")){
                                                Toast.makeText(getApplicationContext(), "loginned as " + type, Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(),DashBoard_Activity.class));
                                                finish();
                                            }else{
                                                Toast.makeText(getApplicationContext(), "loginned as " + type, Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(),Student_View_course.class));
                                                finish();
                                            }

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    */

                                }

                            }
                        });


               // DatabaseReference reference = FirebaseDatabase.getInstance().getReference();


            }
        });






    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String Item = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(), "Selected : "+Item, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        ///**********************
    }





}



/*

TabHost tabs = (TabHost) findViewById(R.id.tabhost);
      tabs.setup();
      TabHost.TabSpec spec = tabs.newTabSpec("tag1");
      spec.setContent(R.id.tab1);
      spec.setIndicator("First");
      tabs.addTab(spec);
      spec = tabs.newTabSpec("tag2");
      spec.setContent(R.id.tab2);
      spec.setIndicator("second");
      tabs.addTab(spec);

 */