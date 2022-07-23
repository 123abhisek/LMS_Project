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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashBoard_Activity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private DrawerLayout drawerLayout; //Drawer Layout
    private NavigationView navigationView; //Navigation View
    private Toolbar toolbar;  //Toolbar

    private CardView c1;
    private CardView c2;
    private CardView c3;
    private CardView c4;
    private CardView c5;
    private CardView c6;

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        mAuth = FirebaseAuth.getInstance(); // creating instance of Firebase authentication

        setUpToolbar();
        setUpNavigationDrawerMenu();

        // cards references
        c1 = (CardView) findViewById(R.id.view_course_card);  //View Course
        //c2 = (CardView) findViewById(R.id.attendence_card);  // Attendance
        //c3 = (CardView) findViewById(R.id.result_card);  //Result
        c4 = (CardView) findViewById(R.id.settings_card);  // settings
        //c5 = (CardView) findViewById(R.id.profile_card); // Profile
        //c6 = (CardView) findViewById(R.id.profile_card);

        tv = (TextView)findViewById(R.id.emailname);

        //View Course card
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUser mFirebaseuser = mAuth.getCurrentUser();
                Bundle bundle = new Bundle();
                bundle.putString("value",mFirebaseuser.getEmail());
                Toast.makeText(getApplicationContext(), "View Course", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(getApplicationContext(),View_Course.class);
                in.putExtra("name",bundle);
                startActivity(in);

            }
        });

        //Attendance card
       /* c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Attendance clicked", Toast.LENGTH_SHORT).show();
            }
        });

        */

        //Analysis card
        /*
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Analysis clicked", Toast.LENGTH_SHORT).show();
            }
        });

         */

        //Settings card
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser mFirebaseuser = mAuth.getCurrentUser();
                Bundle bundle = new Bundle();
                bundle.putString("value",mFirebaseuser.getEmail());
                Toast.makeText(getApplicationContext(), "Settings clicked", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(getApplicationContext(),Profile_Activity.class);
                in.putExtra("name",bundle);
                startActivity(in);

                //Bundle bundle = new Bundle();
                //bundle.putString("value",getCourse_name);
                //Intent in = new Intent(getApplicationContext(),Add_Course_Module.class);
                //in.putExtra("name",bundle);
                //startActivity(in);

            }
        });

        /*
        //Profile card
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Profile clicked", Toast.LENGTH_SHORT).show();
            }
        });

         */



    }


    //Creating Navigation Drawer
    private void setUpNavigationDrawerMenu() {

        navigationView = (NavigationView) findViewById(R.id.navigationView); //reference of navigation view
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout); // reference of drawer layout



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);  //creating object of ActionBarLayout
        drawerLayout.addDrawerListener(toggle); //Adding toggle to the drawer layout
        toggle.syncState(); // syncing the toggle

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                String itemName = (String) item.getTitle();
                Toast.makeText(getApplicationContext(), itemName +" Clicked", Toast.LENGTH_SHORT).show();

                closeDrawer();

                switch (item.getItemId()){
                    case R.id.Item_home:
                        Intent in = new Intent(getApplicationContext(),DashBoard_Activity.class);
                        startActivity(in);
                        break;

                    case R.id.Add_Course:
                        FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
                        Bundle bundle = new Bundle();
                        bundle.putString("value",mFirebaseUser.getEmail());
                        Toast.makeText(getApplicationContext(), "View Course", Toast.LENGTH_SHORT).show();
                        Intent in2 = new Intent(getApplicationContext(),Add_Course1.class);
                        in2.putExtra("name",bundle);
                        startActivity(in2);


                        break;

                    case R.id.View_Course:

                        FirebaseUser mFirebaseuser = mAuth.getCurrentUser();
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("value",mFirebaseuser.getEmail());
                        Toast.makeText(getApplicationContext(), "View Course", Toast.LENGTH_SHORT).show();
                        Intent in1 = new Intent(getApplicationContext(),View_Course.class);
                        in1.putExtra("name",bundle1);
                        startActivity(in1);


                        break;

                    case R.id.About:
                        break;



                    case R.id.Logout:
                        logout();
                        break;

                }
                return true;
            }
        });


    }

    // setting Toolbar
    private void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    // setting Close Drawer
    private void closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            closeDrawer();
            finish();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.None:
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser mFirebaseuser = mAuth.getCurrentUser();
        if (mFirebaseuser != null){
            // there is some user
            tv = (TextView)findViewById(R.id.emailname);
            tv.setText(mFirebaseuser.getEmail());
            Toast.makeText(this, "name is : "+mFirebaseuser.getEmail() +" /", Toast.LENGTH_SHORT).show();

        }else {
            // no one logout
            startActivity(new Intent(getApplicationContext(),loginActivity.class));
            finish();
        }

    }

    public void logout(){
        mAuth.signOut();
        startActivity(new Intent(getApplicationContext(),loginActivity.class));
        finish();
    }
}