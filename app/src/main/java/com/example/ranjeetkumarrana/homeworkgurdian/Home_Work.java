package com.example.ranjeetkumarrana.homeworkgurdian;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Home_Work extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "DETAILS";
    FloatingActionButton floatingActionButton;
    final Context context = this;

    Spinner spinner_expense_type,site_type_spinner;
    Button btn_date_expense,update,cancer;
    EditText expense_price,descriptionEx;
    TextView date_expense;

    private int mYear, mMonth, mDay, mHour, mMinute;

    DatabaseReference databaseReferenceSI;

    ListView listViewSiemens;
    List<Home_Work_Details> Slist;
    String[] country = { "1st", "2nd", "3rd", "4th", "5th","6th","7th","8th","9th","10th","11th","12th"};
    String[] country2 = { "English", "Hindi", "Science", "History", "Mathmatics"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__work);

        databaseReferenceSI = FirebaseDatabase.getInstance().getReference();

        floatingActionButton = findViewById(R.id.floating_Ho_Wok);

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("Expense Traking");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        listViewSiemens = findViewById(R.id.listView_HW);
        Slist= new ArrayList<>();

        // spinner_expense_type.setOnItemSelectedListener(SiemensSite.this);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(Home_Work.this);
                dialog.setContentView(R.layout.dialog_home_work);
                spinner_expense_type= dialog.findViewById(R.id.expense_type_spinner);
                site_type_spinner= dialog.findViewById(R.id.site_type_spinner);
                btn_date_expense= dialog.findViewById(R.id.btn_date_expense);
                date_expense= dialog.findViewById(R.id.date_expense);
                expense_price= dialog.findViewById(R.id.expense_price);
                descriptionEx = dialog.findViewById(R.id.expense_discription);
                update = dialog.findViewById(R.id.update_ex);
                cancer = dialog.findViewById(R.id.cancer_ex);


                ArrayAdapter aa = new ArrayAdapter(Home_Work.this,android.R.layout.simple_spinner_item,country);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_expense_type.setAdapter(aa);
                spinner_expense_type.setOnItemSelectedListener(Home_Work.this);


                ArrayAdapter aa1 = new ArrayAdapter(Home_Work.this,android.R.layout.simple_spinner_item,country2);
                aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                site_type_spinner.setAdapter(aa1);
                site_type_spinner.setOnItemSelectedListener(Home_Work.this);


                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Siemens();

                    }
                });

                cancer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btn_date_expense.setOnClickListener(this);
                btn_date_expense.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final Calendar c = Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(Home_Work.this,
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {

                                        date_expense.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                    }
                                }, mYear, mMonth, mDay);
                        datePickerDialog.show();

                    }
                });



                dialog.show();

            }
        });

    }

    private void Siemens(){

        String expenseTypeSpinner = spinner_expense_type.getSelectedItem().toString();
        String expense_site = site_type_spinner.getSelectedItem().toString();
        String dateExpense = date_expense.getText().toString();
        String priceEx = expense_price.getText().toString();
        String description = descriptionEx.getText().toString();

        if(!TextUtils.isEmpty(expenseTypeSpinner)) {

//                 String id = databaseReferenceSI.push().getKey();


            Home_Work_Details siemensDetails = new Home_Work_Details(String.valueOf(1), expenseTypeSpinner, expense_site, dateExpense, priceEx, description);


            databaseReferenceSI.child("details").push().setValue(siemensDetails, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                    Log.d(TAG, "onComplete: " + databaseReference.getKey());
                }
            });


            Log.d("Show", databaseReferenceSI.toString() + " **** " + siemensDetails.getSiemensId() +
                    siemensDetails.getDateExpense_D() + siemensDetails.getDescription_D()
            );



//
//                 Toast.makeText(this, "Siemens Type added", Toast.LENGTH_SHORT).show();
//             }else {
//                 Toast.makeText(this, "you should enter the Siemens Type", Toast.LENGTH_SHORT).show();
//             }

        } }

    @Override
    protected void onStart() {
        super.onStart();

        final DatabaseReference reference  = FirebaseDatabase.getInstance().getReference("details");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Slist.clear();
                for (DataSnapshot siemensSnapshot: dataSnapshot.getChildren()){
                    Home_Work_Details siemensDetail = siemensSnapshot.getValue(Home_Work_Details.class);
                    Slist.add(siemensDetail);

                }

                Home_Work_List adapter = new Home_Work_List(Home_Work.this,Slist);
                listViewSiemens.setAdapter(adapter);

                Log.d("Adapter","adpater"+listViewSiemens);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
