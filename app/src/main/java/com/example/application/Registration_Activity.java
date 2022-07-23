package com.example.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registration_Activity extends AppCompatActivity {

    FirebaseAuth mAuth;
    final String TAG = "Registration_Activity";

    public  static final  String SHARED_PREFS = "shared_prefs";
    public  static final  String EMAIL_KEY = "email_key";
    public  static final  String PASSWORD_KEY = "password_key";

    SharedPreferences sharedPreferences ;
    String email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginBtn = (Button) findViewById(R.id.btnLogin);

        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        email = sharedPreferences.getString(EMAIL_KEY,null);
        password = sharedPreferences.getString(PASSWORD_KEY,null);

        final EditText usernameInput = (EditText) findViewById(R.id.inputUsername);
        final EditText passwordInput = (EditText) findViewById(R.id.inputPassword);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                if (TextUtils.isEmpty(usernameInput.getText().toString() ) && TextUtils.isEmpty(passwordInput.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Please enter Email and Password ..",Toast.LENGTH_LONG).show();
                }else{
                    SharedPreferences.Editor Editor = sharedPreferences.edit();
                    Editor.putString(EMAIL_KEY,usernameInput.getText().toString());
                    Editor.putString(PASSWORD_KEY,passwordInput.getText().toString());
                    Editor.apply();

                }

                login(username,password);
            }
        });

        TextView registerLink = (TextView) findViewById(R.id.registerLink);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration_Activity.this,MainActivity.class));
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        if (email != null && password != null){
            Intent in = new Intent(getApplicationContext(),DashBoard_Activity.class);
            startActivity(in);
        }
    }

    private void login(String username, String password) {

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null){

            Intent in = new Intent(Registration_Activity.this,DashBoard_Activity.class);
            startActivity(in);
            finish();

        }

       /* mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            //Sign in success , update UI with the signed in users
                            Log.d(TAG,"signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Toast.makeText(MainActivity.this, "Authentication Success", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(MainActivity.this,MainActivity.class));
                            finish();

                        }else {
                            Log.d(TAG,"signInWithEmail:failure",task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }


                    }
                });

        */

    }

    public void checkLogin(){

    }

   /* @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
            startActivity(new Intent(Registration_Activity.this,MainActivity.class));
            finish();
        }

    }

    */
}