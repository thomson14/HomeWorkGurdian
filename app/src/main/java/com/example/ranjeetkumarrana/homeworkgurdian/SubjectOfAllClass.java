package com.example.ranjeetkumarrana.homeworkgurdian;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SubjectOfAllClass extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    private Toolbar toolbar;
    private TextView NursClass,LKGClass,UKGClass,OneClass,TwoClass,ThreeClass,FourClass,FiveClass,SixClass,SevenClass,EigthClass,NineClass,TenClass;
    String[] users1 = { "Nursery", "LKG", "UKG", "One", "Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten" };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_of_all_class);

        //todo-----------------Toolbar-----------------//
        toolbar = findViewById(R.id.toolbar2);
        toolbar.setTitle("Subjects");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(SubjectOfAllClass.this, MainActivityAllDetails.class));
                finish();
            }
        });
        //todo-------------------------------end toolbar--------------------------------//


        //todo-----------------TexttView init-----------------------------------//

        NursClass = (TextView)findViewById(R.id.firstSub);
        UKGClass = (TextView)findViewById(R.id.secondSub);
        LKGClass = (TextView)findViewById(R.id.thirdSub);
        OneClass = (TextView)findViewById(R.id.fourSub);
        TwoClass = (TextView)findViewById(R.id.fiveSub);
        ThreeClass = (TextView)findViewById(R.id.sixSub);
        FourClass = (TextView)findViewById(R.id.sevenSub);
        FiveClass = (TextView)findViewById(R.id.eightSub);
        SixClass = (TextView)findViewById(R.id.nineSub);
        //todo------------------End---------------------------------------------//


        Spinner spin = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        String str = users1[position];

        NursClass.setText(str);
        UKGClass.setText(str);
        LKGClass.setText(str);
        OneClass.setText(str);
        TwoClass.setText(str);
        ThreeClass.setText(str);
        FourClass.setText(str);
        FiveClass.setText(str);
        SixClass.setText(str);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}
