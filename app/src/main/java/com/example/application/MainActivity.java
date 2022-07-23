package com.example.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    final String TAG = "MainActivity";

    /*public  static final  String SHARED_PREFS = "shared_prefs";
    public  static final  String EMAIL_KEY = "email_key";
    public  static final  String PASSWORD_KEY = "password_key";

    SharedPreferences sharedPreferences ;
    String email,password;

     */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Button registerBtn = (Button) findViewById(R.id.btnRegister);
        Button loginBtn = (Button) findViewById(R.id.btnLogin);

        /*sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        email = sharedPreferences.getString(EMAIL_KEY,null);
        password = sharedPreferences.getString(PASSWORD_KEY,null);

         */

        EditText usernameInput = (EditText) findViewById(R.id.inputUsername);
        EditText passwordInput = (EditText) findViewById(R.id.inputPassword);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signUp();

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usernameInput = (EditText) findViewById(R.id.inputUsername);
                EditText passwordInput = (EditText) findViewById(R.id.inputPassword);

                /*if (TextUtils.isEmpty(usernameInput.getText().toString() ) && TextUtils.isEmpty(passwordInput.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Please enter Email and Password ..",Toast.LENGTH_LONG).show();
                }else{
                    SharedPreferences.Editor Editor = sharedPreferences.edit();
                    Editor.putString(EMAIL_KEY,usernameInput.getText().toString());
                    Editor.putString(PASSWORD_KEY,passwordInput.getText().toString());
                    Editor.apply();

                }

                 */

                Intent in = new Intent(getApplicationContext(),Registration_Activity.class);
                startActivity(in);

            }
        });


    }

    private void signUp() {

        EditText usernameInput = (EditText) findViewById(R.id.inputUsername);
        EditText passwordInput = (EditText) findViewById(R.id.inputPassword);

        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            //Sign in success , update UI with the signed in users
                            Log.d(TAG,"createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Toast.makeText(MainActivity.this, "Authentication Success" + user.getEmail(), Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(MainActivity.this, Registration_Activity.class));
                            finish();

                        }else{
                            Log.d(TAG,"createUserWithEmail:failure",task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

   /* @Override
    protected void onStart() {
        super.onStart();
        if(email != null && password != null){
            Intent in = new Intent(getApplicationContext(),DashBoard_Activity.class);
            startActivity(in);
        }
    }

    */
}


/*

      Button registerBtn = (Button) findViewById(R.id.btnRegister);

        final EditText usernameInput = (EditText) findViewById(R.id.inputUsername);
        final EditText passwordInput = (EditText) findViewById(R.id.inputPassword);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                signUp(username,password);

            }
        });

    }

    private void signUp(String username, String password) {

        mAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            //Sign in success , update UI with the signed in users
                            Log.d(TAG,"createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Toast.makeText(Registration_Activity.this, "Authentication Success" + user.getEmail(), Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(Registration_Activity.this, DashBoard_Activity.class));
                            finish();

                        }else{
                            Log.d(TAG,"createUserWithEmail:failure",task.getException());
                            Toast.makeText(Registration_Activity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

 */