package com.example.ranjeetkumarrana.homeworkgurdian;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Academic_Calender extends AppCompatActivity
{

    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;

    private RecyclerView recyclerView;
    List<ListItem> list;
    ItemAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic__calender);

        recyclerView = (RecyclerView) findViewById(R.id.recyleView);
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
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar1);
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
                intent.setType("*/*");
                startActivityForResult(intent, 7);
            }
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
    }
}