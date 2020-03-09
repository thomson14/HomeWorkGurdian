package com.example.ranjeetkumarrana.homeworkgurdian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private Button viewResult;
    private TextView showResult;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //todo-----------------Toolbar-----------------//
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar3);
        toolbar.setTitle("Result");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //mActionBar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(ResultActivity.this, MainActivityAllDetails.class));
                finish();
            }
        });
        //todo-------------------------------end toolbar--------------------------------//

        viewResult = (Button)findViewById(R.id.ViewButton);
        showResult = (TextView)findViewById(R.id.dispalayResult);
        viewResult.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showResult.setVisibility(View.VISIBLE);
                showResult.setText("Your Child is get 80%");
            }
        });

    }
}
