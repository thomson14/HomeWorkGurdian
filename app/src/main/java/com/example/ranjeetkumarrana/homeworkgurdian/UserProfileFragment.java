package com.example.ranjeetkumarrana.homeworkgurdian;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment
{
    private FloatingActionButton editProfileButton;


    public UserProfileFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.fragment_user_profile, container, false);
        // Inflate the layout for this fragment
        editProfileButton = (FloatingActionButton)view.findViewById(R.id.editButton);

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
