package com.example.application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class upload_PDF_notes extends AppCompatActivity {

    EditText editText;
    Button upload;

    Toolbar toolbar_upload;

    StorageReference storageReference;
    DatabaseReference rootRef , course_Ref ,pdf_uploadRef ,moduleRef,pdf_uploadRef_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pdf_notes);

        toolbar_upload = findViewById(R.id.toolbar_upload_pdf);
        editText = findViewById(R.id.edittext_pdf_upload);
        upload = findViewById(R.id.button_upload);

        //Intent Content
        Intent i = getIntent();
        Bundle b = i.getBundleExtra("course_and_module");

        String getCourse_nameValue = b.getString("course_name_vale");
        String getModule_name = b.getString("module_name_value");

        //Toolbar
        toolbar_upload.setTitle(getModule_name);
        setSupportActionBar(toolbar_upload);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar_upload.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back));
        toolbar_upload.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(getApplicationContext(),Add_Course_Module.class);
                startActivity(in);
            }
        });

        storageReference = FirebaseStorage.getInstance().getReference();
        rootRef = FirebaseDatabase.getInstance().getReference();
        pdf_uploadRef = rootRef.child("Upload_Notes");


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = editText.getText().toString().trim();
                if (value.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Invalid Input ....", Toast.LENGTH_SHORT).show();
                }else {
                    selectPDFFile();
                }
            }
        });
    }

    private void selectPDFFile() {

        //upload pdf
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);

        // We will be redirected to choose pdf
        intent.setType("application/pdf");
        startActivityForResult(Intent.createChooser(intent,"Select PDF file "),1);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            uploadPDFFile(data.getData());
        }
    }

    private void uploadPDFFile(Uri data) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading.....");
        progressDialog.show();

        StorageReference reference = storageReference.child("upload/"+System.currentTimeMillis()+".pdf");
        reference.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        // ****
                        Intent i = getIntent();
                        Bundle b = i.getBundleExtra("course_and_module");

                        String getCourse_nameValue = b.getString("course_name_vale");
                        String getModule_name = b.getString("module_name_value");

                        if (getCourse_nameValue.matches("") && getModule_name.matches("")){
                            Toast.makeText(getApplicationContext(), "There is no data ! ..", Toast.LENGTH_SHORT).show();
                        }else {

                            String name = editText.getText().toString();

                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                            while (!uri.isComplete());
                            Uri url = uri.getResult();

                            uploadPDF uploadPDF = new uploadPDF(url.toString(),name);

                            // Upload Notes --> Course name  --> Module Name  --> upload file name
                            course_Ref = pdf_uploadRef.child(getCourse_nameValue); // course ref
                            moduleRef = course_Ref.child(getModule_name); // module ref
                            pdf_uploadRef_value = moduleRef.child(name); // upload file name
                            pdf_uploadRef_value.setValue(uploadPDF); // set value


                            //rootRef.child(rootRef.push().getKey()).setValue(uploadPDF);

                            Toast.makeText(upload_PDF_notes.this, "File uploaded ", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                        double progress = (100.0 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                        progressDialog.setMessage("Uploaded : "+(int) progress +"%");

                    }
                });


    }

}