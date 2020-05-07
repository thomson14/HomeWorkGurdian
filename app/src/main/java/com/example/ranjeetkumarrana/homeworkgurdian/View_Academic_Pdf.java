package com.example.ranjeetkumarrana.homeworkgurdian;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.collection.LLRBNode;

import java.util.ArrayList;
import java.util.List;

public class View_Academic_Pdf extends AppCompatActivity {

    ListView listCalender;
    DatabaseReference  databaseReference;
    List<UploadPdf> uploadPdfs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__academic__pdf);

        listCalender = findViewById(R.id.listCalender);
        uploadPdfs = new ArrayList<>();


        viewAllFiles();

        listCalender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                UploadPdf uploadPdf = uploadPdfs.get(i);
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(uploadPdf.getUrl()));
                startActivity(intent);
            }
        });

    }

    public void viewAllFiles(){

        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    UploadPdf uploadPdf = postSnapshot.getValue(UploadPdf.class);
                    uploadPdfs.add(uploadPdf);
                }

                String[] uploads = new String[uploadPdfs.size()];
                for (int  i=0 ; i<uploads.length ; i++)
                {
                    uploads[i] = uploadPdfs.get(i).getNamePDF();
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,uploads){

                    @Override
                    public View getView(int position,View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView myText =   (TextView) view.findViewById(android.R.id.text1);
                        myText.setTextColor(Color.BLACK);
                        return view;
                    }
                };
                listCalender.setAdapter(arrayAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(View_Academic_Pdf.this, "DO NOT Work", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
