package com.example.ranjeetkumarrana.homeworkgurdian;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.telecom.Call;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivityAllDetails extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private TextView user_name,user_email;
    private boolean admin = false;

    private CardView Attendance,AcedmicCalender,Notification,Subject,Result,HomeWork;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_all_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //todo----User Image , Name and Email Details in navigation Header---//
            user_name = (TextView)findViewById(R.id.userName1) ;
            user_email = (TextView)findViewById(R.id.userMail);

            Intent intent = getIntent();
            String EDT_NAME = intent.getStringExtra("NAME");
            String EDT_EMAIL = intent.getStringExtra("EMAIL");

        //todo-------End Here-----//



        /*todo;--------------All Card View are initialized --------------*/

        Attendance = (CardView)findViewById(R.id.AttendanceId);
        AcedmicCalender = (CardView)findViewById(R.id.ACID);
        Notification = (CardView)findViewById(R.id.NotificationID);
        Result = (CardView)findViewById(R.id.ResultID);
        HomeWork = (CardView)findViewById(R.id.homeWorkID);
        Subject = (CardView)findViewById(R.id.SubjectID);

        Attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               Toast.makeText(MainActivityAllDetails.this,"you clicked  Attendance",Toast.LENGTH_LONG).show();
            }
        });

        AcedmicCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivityAllDetails.this,Academic_Calender.class));
            }
        });

        Notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(MainActivityAllDetails.this,"you clicked Notification",Toast.LENGTH_LONG).show();
            }
        });

        Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivityAllDetails.this,ResultActivity.class));
                //Toast.makeText(MainActivityAllDetails.this,"you clicked Result",Toast.LENGTH_LONG).show();
            }
        });

        HomeWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(MainActivityAllDetails.this,"you clicked Home Work",Toast.LENGTH_LONG).show();
            }
        });

        Subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivityAllDetails.this,SubjectOfAllClass.class));
            }
        });

        /*todo:---------------------- End Here----------------------------*/


    }


    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_all_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.admin)
        {
            showRegisterDialog();
            //return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showRegisterDialog()
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        LayoutInflater inflater = LayoutInflater.from(this);
        View register_layout = inflater.inflate(R.layout.admin_login,null);

        final MaterialEditText edtName = register_layout.findViewById(R.id.adminUsername);
        final MaterialEditText edtPassword = register_layout.findViewById(R.id.adminPassword);

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
                    Toast.makeText(MainActivityAllDetails.this,"Please Enter Valid User Name",Toast.LENGTH_LONG).show();
                }

                //Email validation
                else if(TextUtils.isEmpty(edtPassword.getText().toString()))
                {
                    Toast.makeText(MainActivityAllDetails.this,"Please Enter Valid Password",Toast.LENGTH_LONG).show();
                }

                if(edtName.getText().toString() == "admin" && edtPassword.getText().toString() == "123456789")
                {

                }

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home)
        {
            startActivity(new Intent(MainActivityAllDetails.this,MainActivityAllDetails.class));
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle("Home");
        }
        else if (id == R.id.profile)
        {

            UserProfileFragment userProfileFragment = new UserProfileFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainLayout,userProfileFragment
                    ,userProfileFragment.getTag()).commit();
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle("My Profile");
        }
        else if(id == R.id.chat)
        {
            ChattingFragment chattingFragment = new ChattingFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainLayout,chattingFragment
                    ,chattingFragment.getTag()).commit();
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle("Chat");
        }
        else if (id == R.id.nav_share)
        {

        }
        else if (id == R.id.logout)
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
