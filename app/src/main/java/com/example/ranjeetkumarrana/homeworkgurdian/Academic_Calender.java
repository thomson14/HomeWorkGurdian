package com.example.ranjeetkumarrana.homeworkgurdian;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;

import android.os.Bundle;
import android.os.storage.StorageManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class Academic_Calender extends AppCompatActivity {

  /*  private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;

    private RecyclerView recyclerView;
    List<ListItem> list;
    ItemAdapter adapter;*/

   Button btnCalender,btnViewCalender;
   TextInputEditText edtCalender;
   StorageReference storageReference;
   DatabaseReference databaseReference;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic__calender);

            btnCalender = (Button) findViewById(R.id.btnCalender);
           // btnViewCalender = findViewById(R.id.btnViewCalender);
            edtCalender = (TextInputEditText) findViewById(R.id.editCalender);

            storageReference = FirebaseStorage.getInstance().getReference();
            databaseReference = FirebaseDatabase.getInstance().getReference("uploads");

//            btnViewCalender.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    startActivity(new Intent(getApplicationContext(),View_Academic_Pdf.class));
//                }
//            });

            btnCalender.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectPdfFile();
                }
            });

        //*****************************************************************************************
        /*recyclerView = (RecyclerView) findViewById(R.id.recyleView);
        list = new ArrayList<>();
        list.add(new ListItem("Name:- Ranjeet Kumar Rana", "This is old Schedule"));
        list.add(new ListItem("Name:- Ranjeet Kumar Rana", "This is old Schedule"));
        list.add(new ListItem("Name:- Ranjeet Kumar Rana", "This is old Schedule"));
        list.add(new ListItem( "Name:- Ranjeet Kumar Rana", "This is old Schedule"));
        list.add(new ListItem( "Name:- Ranjeet Kumar Rana", "This is old Schedule"));


        adapter = new ItemAdapter(this, list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //todo-----------------Toolbar-----------------//
        toolbar =  findViewById(R.id.toolbar1);
        toolbar.setTitle("Academic Calender");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //mActionBar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Academic_Calender.this, MainActivityAllDetails.class));
                finish();
            }
        });
        //todo-------------------------------end toolbar--------------------------------//

        floatingActionButton = (FloatingActionButton) findViewById(R.id.Add_New_Calender);
        floatingActionButton.setBackground(new ColorDrawable(Color.parseColor("#FFFFFF")));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType();
              //  startActivityForResult(intent, 7);
            /*}
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        switch (requestCode)
        {
            case 7:
                if(requestCode == RESULT_OK)
                {
                    String pathHolder = data.getData().getPath();
                    Toast.makeText(Academic_Calender.this,"Successfully Uploaded",Toast.LENGTH_LONG).show();
                }
                break;
        }
        //super.onActivityResult(requestCode, resultCode, data);
    }*/
    }

    public void selectPdfFile(){
          Intent intent = new Intent();
          intent.setType("application/pdf");
          intent.setAction(Intent.ACTION_GET_CONTENT);
          startActivityForResult(Intent.createChooser(intent,"select PDF File"),1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data !=null && data.getData() != null){
            uploadPDFFile(data.getData());
        }
    }

    public void uploadPDFFile(Uri data){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading.......");
        progressDialog.show();

        StorageReference reference = storageReference.child("uploads/"+System.currentTimeMillis()+".pdf");
        reference.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uri.isComplete());
                        Uri url = uri.getResult();

                        UploadPdf uploadPdf = new UploadPdf(edtCalender.getText().toString(),url.toString());
                        databaseReference.child(databaseReference.push().getKey()).setValue(uploadPdf);
                        Toast.makeText(Academic_Calender.this, "File Uploaded", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {

                double progress = (100.0*taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                progressDialog.setMessage("uploaded: "+(int)progress+"%");

            }
        });

    }

    public void btn_action(View view) {
        startActivity(new Intent(getApplicationContext(),View_Academic_Pdf.class));
    }
}
