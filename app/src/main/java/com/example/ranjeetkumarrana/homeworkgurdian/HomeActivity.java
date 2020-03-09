package com.example.ranjeetkumarrana.homeworkgurdian;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


//import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

public class HomeActivity extends AppCompatActivity
{
    private ImageView logo;
    private ConstraintLayout rootLayout;
    private TextView signUpId;
    private static int splashTimeOut=5000;
    private Animation animFadein;
    private Button LoginBtnID;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //todo--My animation is start from here
        logo = (ImageView)findViewById(R.id.imageAtLogin);
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.splash_screen);
        logo.startAnimation(myanim);
        //todo--My animation is end here

        signUpId = (TextView)findViewById(R.id.signId);

        signUpId.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showRegisterDialog();
            }
        });


        LoginBtnID = (Button)findViewById(R.id.loginID);
        LoginBtnID.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showLoginDialog();
            }
        });

    }

    private void showLoginDialog()
    {

        final MaterialEditText edtEmail,edtPassword;
        edtEmail = (MaterialEditText)findViewById(R.id.emailID);
        edtPassword = (MaterialEditText)findViewById(R.id.passwordID);


        final String user_mail = edtEmail.getText().toString();
        final String user_password = edtPassword.getText().toString();

        if (TextUtils.isEmpty(user_mail))
        {
            Toast.makeText(HomeActivity.this,"This feild is required",Toast.LENGTH_LONG).show();
        }

        //Name validation
        if (user_password.length() < 3)
        {
            Toast.makeText(HomeActivity.this,"This feild is required",Toast.LENGTH_LONG).show();
        }
        startActivity(new Intent(HomeActivity.this,MainActivityAllDetails.class));
        finish();

    }

    private void showRegisterDialog()
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        LayoutInflater inflater = LayoutInflater.from(this);
        View register_layout = inflater.inflate(R.layout.sign_up,null);

        final MaterialEditText edtName = register_layout.findViewById(R.id.edtName);
        final MaterialEditText edtEmail = register_layout.findViewById(R.id.edtEmail);
        final MaterialEditText edtPassword = register_layout.findViewById(R.id.edtPassword);
        final MaterialEditText edtPhone = register_layout.findViewById(R.id.edtPhone);


        dialog.setView(register_layout);

        //set button

        dialog.setPositiveButton("REGISTER", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int which)
            {
                dialogInterface.dismiss();

                //check validation

                //Name validation
                if(TextUtils.isEmpty(edtName.getText().toString()))
                {
                    Toast.makeText(HomeActivity.this,"This feild is required",Toast.LENGTH_LONG).show();
                }

                //Email validation
                else if(TextUtils.isEmpty(edtEmail.getText().toString()))
                {
                    Toast.makeText(HomeActivity.this,"This feild is required",Toast.LENGTH_LONG).show();
                }

                //Name validation
                else if(edtPassword.getText().toString().length()<6)
                {
                    Toast.makeText(HomeActivity.this,"This feild is required",Toast.LENGTH_LONG).show();
                }

                //Phone validation
                else if(edtPhone.getText().toString().length()<10)
                {
                    Toast.makeText(HomeActivity.this,"This feild is required",Toast.LENGTH_LONG).show();
                }
                startActivity(new Intent(HomeActivity.this,MainActivityAllDetails.class));
                finish();

            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int which)
            {
                dialogInterface.dismiss();
            }
        });

        dialog.show();

    }

}
