package com.example.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Add_Course_Module extends AppCompatActivity {

    TextView TVCourseNameTitle ,TVCourseName;
    Button addModule;
    Toolbar toolbar;

    String message;

    // upload
    Uri uri ;

    //display module
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    FirebaseRecyclerAdapter adapter;

    String course_module;

    Bundle bundle;

    //Firebase
    DatabaseReference rootRef,courseRef,moduleRef ,pdf_uploadRef,pdf_uploadRef_value;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course_module);

        storageReference = FirebaseStorage.getInstance().getReference();

        addModule = findViewById(R.id.addModule_button);

        toolbar = findViewById(R.id.toolbar);

        rootRef = FirebaseDatabase.getInstance().getReference();

        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("name");

        String getCourse_name = bundle.getString("value");

        if (getCourse_name.matches("")){
            Toast.makeText(this, "There is no data ! ..", Toast.LENGTH_SHORT).show();
        }else {
           // TVCourseName.setText(getCourse_name);
            toolbar.setTitle("Modules");
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back));
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    bundle.putString("value",getCourse_name);
                    Intent in =new Intent(getApplicationContext(),Course_function.class);
                    in.putExtra("name",bundle);
                    startActivity(in);
                }
            });
        }

        View view1 = getLayoutInflater().from(Add_Course_Module.this).inflate(R.layout.alert_dailog_add_module,null);
        EditText ET_moduleName = (EditText) view1.findViewById(R.id.edittext_addModule);
        course_module = ET_moduleName.getText().toString();

        addModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Alert Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(Add_Course_Module.this);
                View view1 = getLayoutInflater().from(Add_Course_Module.this).inflate(R.layout.alert_dailog_add_module,null);
                EditText ET_moduleName = (EditText) view1.findViewById(R.id.edittext_addModule);

                builder .setMessage("Add Module ")
                        .setView(view1)
                        .setPositiveButton("Add Module", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                course_module = ET_moduleName.getText().toString();
                                if(course_module.isEmpty()){
                                    Toast.makeText(Add_Course_Module.this, "Invalid Input ! .....", Toast.LENGTH_SHORT).show();
                                }else{
                                   //add module

                                   rootRef = FirebaseDatabase.getInstance().getReference();
                                   //courseRef = rootRef.child("Course").child();
                                    moduleRef = rootRef.child("Module");
                                    moduleRef.child(getCourse_name).child(course_module).child("module_name").setValue(course_module);


                                    Toast.makeText(Add_Course_Module.this, "Module Added Successfully", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton(" Cancel ",null)
                        .setCancelable(true);

                AlertDialog alert = builder.create();
                alert.show();

            }
        });

        // display modules

        recyclerView = findViewById(R.id.recyclerView_module);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        fetch(getCourse_name ,course_module);


    }

    private void fetch(String getCourse_name ,String course_module) {

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Module").child(getCourse_name);

        FirebaseRecyclerOptions<Course_Module> options = new FirebaseRecyclerOptions.Builder<Course_Module>()
                .setQuery(query, new SnapshotParser<Course_Module>() {
                    @NonNull
                    @Override
                    public Course_Module parseSnapshot(@NonNull DataSnapshot snapshot) {
                        return new Course_Module(snapshot.child("module_name").getValue().toString());
                    }
                })
                .build();

        adapter = new FirebaseRecyclerAdapter<Course_Module,ViewHolder>(options){

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_module_rv_item,parent,false);
                return new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Course_Module model) {
                String value = model.getModule_Name();

                //TextView for module Name
                holder.setTitle(model.getModule_Name());
                holder.title.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("IntentReset")
                    @Override
                    public void onClick(View v) {

                        // Upload Notes --> Course name  --> Module Name  --> upload file name

                    }
                });

                //Image Button to upload pdf
                holder.add_pdf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        bundle = new Bundle();
                        //course name , module name
                        bundle.putString("course_name_vale",getCourse_name);
                        bundle.putString("module_name_value",value);


                        // Pdf alert Dialog
                        Intent intent = new Intent(getApplicationContext(),upload_PDF_notes.class);

                        intent.putExtra("course_and_module",bundle);
                        startActivity(intent);

                    }
                });

                //Image Button to option menu
                holder.invert_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        PopupMenu popup = new PopupMenu(getApplicationContext(),v);
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()){
                                    case R.id.Edit:
                                        Toast.makeText(Add_Course_Module.this, "Edit clicked", Toast.LENGTH_SHORT).show();
                                        break;

                                    case R.id.Delete:
                                        Toast.makeText(Add_Course_Module.this, "Delete clicked", Toast.LENGTH_SHORT).show();
                                        break;

                                    case R.id.View_pdf:

                                        bundle = new Bundle();
                                        //course name , module name
                                        bundle.putString("course_name_value1",getCourse_name);
                                        bundle.putString("module_name_value1",value);

                                        Intent intent = new Intent(getApplicationContext(),View_PDF_activity.class);
                                        intent.putExtra("course_and_module1",bundle);
                                        v.getContext().startActivity(intent);


                                        Toast.makeText(Add_Course_Module.this, "View Pdf clicked", Toast.LENGTH_SHORT).show();
                                        break;

                                    default:
                                        Toast.makeText(Add_Course_Module.this, "Error ! ....", Toast.LENGTH_SHORT).show();
                                }
                                return false;
                            }
                        });
                        MenuInflater inflater = popup.getMenuInflater();
                        inflater.inflate(R.menu.option_menu_pdf,popup.getMenu());
                        popup.show();

                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }


    // View Holder class
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageButton add_pdf , invert_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.module_name_TV);
            add_pdf = itemView.findViewById(R.id.imageView_addBtn);
            invert_btn = itemView.findViewById(R.id.imageView_inertBtn);

        }

        public void setTitle(String string) {
            title.setText(string);
        }
    }

    //onStart method
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    // onStop method
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }



}





















