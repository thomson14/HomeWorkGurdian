package com.example.ranjeetkumarrana.homeworkgurdian;


import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment
{
    private FloatingActionButton editProfileButton;
    private TextView nameprofile,mobileprofile,emailprofile;
    FirebaseDatabase firebaseDatabase_profile;
    DatabaseReference databaseReference_profile;


    public UserProfileFragment()
    {
        // Required empty public constructor



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.fragment_user_profile, container, false);
        // Inflate the layout for this fragment
        editProfileButton = (FloatingActionButton)view.findViewById(R.id.editButton);
        nameprofile = view.findViewById(R.id.userName_profile);
        mobileprofile  = view.findViewById(R.id.userName_profile);
        emailprofile = view.findViewById(R.id.userEmail_profile);
        firebaseDatabase_profile = FirebaseDatabase.getInstance();
        databaseReference_profile = firebaseDatabase_profile.getReference("Users");



        editProfileButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Snackbar.make(v, "You want to edit your profile", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        return view;//inflater.inflate(R.layout.fragment_user_profile, container, false);
    }

}
