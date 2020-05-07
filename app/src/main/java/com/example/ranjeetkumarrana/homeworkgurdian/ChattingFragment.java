package com.example.ranjeetkumarrana.homeworkgurdian;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.net.URLEncoder;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChattingFragment extends Fragment
{
    private Button A1,B2,C3;
    private EditText tech1,tech2,tech3;
    private Button call1,call2,call3;
    private Context context;


    public ChattingFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_chatting, container, false);

        A1 = (Button) view.findViewById(R.id.A);
        B2 = (Button) view.findViewById(R.id.B);
        C3 = (Button) view.findViewById(R.id.C);

        tech1 = (EditText)view.findViewById(R.id.TechMob1);
        tech2 = (EditText)view.findViewById(R.id.TechMob2);
        tech3 = (EditText)view.findViewById(R.id.TechMob3);

        call1 = (Button)view.findViewById(R.id.callTech1);
        call2 = (Button)view.findViewById(R.id.callTech2);
        call3 = (Button)view.findViewById(R.id.callTech3);

        A1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String number = tech1.getText().toString();
                Uri uri = Uri.parse("smsto:" + number);
                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                i.setPackage("com.whatsapp");
                startActivity(Intent.createChooser(i, ""));
            }
        });

        B2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String number = tech2.getText().toString();
                Uri uri = Uri.parse("smsto:" + number);
                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                i.setPackage("com.whatsapp");
                startActivity(Intent.createChooser(i, ""));
            }
        });

        C3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String number = tech3.getText().toString();
                Uri uri = Uri.parse("smsto:" + number);
                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                i.setPackage("com.whatsapp");
                startActivity(Intent.createChooser(i, ""));
            }
        });

        call1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String phone = tech1.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String temp = "tel:" + phone;
                intent.setData(Uri.parse(temp));
                startActivity(intent);
            }
        });

        call2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String phone = tech2.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String temp = "tel:" + phone;
                intent.setData(Uri.parse(temp));
                startActivity(intent);
            }
        });

        call3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String phone = tech3.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String temp = "tel:" + phone;
                intent.setData(Uri.parse(temp));
                startActivity(intent);
            }
        });

        return view;
    }

}
