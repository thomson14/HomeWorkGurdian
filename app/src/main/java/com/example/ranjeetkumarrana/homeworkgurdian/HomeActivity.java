package com.example.ranjeetkumarrana.homeworkgurdian;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.github.florent37.materialtextfield.MaterialTextField;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

public class HomeActivity extends AppCompatActivity
{
    private ImageView logo;
    private ConstraintLayout rootLayout;
    private TextView signUpId;
    private static int splashTimeOut=5000;
    private Animation animFadein;
    private Button LoginBtnID;
    private MaterialTextField edtEmail,edtPassword;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //todo--My animation is start from here
        logo = (ImageView) findViewById(R.id.imageAtLogin);
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.splash_screen);
        logo.startAnimation(myanim);
        //todo--My animation is end here

        signUpId = (TextView) findViewById(R.id.signId);

        signUpId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        LoginBtnID = (Button) findViewById(R.id.loginID);
        edtEmail = (MaterialTextField) findViewById(R.id.emailID);
        edtPassword = (MaterialTextField) findViewById(R.id.passwordID);
        auth = FirebaseAuth.getInstance();

        LoginBtnID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 String txt_email = edtEmail.getEditText().getText().toString();
                 String txt_password = edtPassword.getEditText().getText().toString();

                if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                    Toast.makeText(HomeActivity.this,"All Fields Are Requierds",Toast.LENGTH_SHORT).show();

                }else {

                    auth.signInWithEmailAndPassword(txt_email,txt_password).
                            addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        //loginProgress.setVisibility(View.INVISIBLE);
                                        Intent intent = new Intent(HomeActivity.this, MainActivityAllDetails.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }else {
                                        Toast.makeText(HomeActivity.this, "Authentication Failed!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });







       /* LoginBtnID.setOnClickListener(new View.OnClickListener()
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

    } */

 /*   private void showRegisterDialog()
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

    }*/
    }
}
