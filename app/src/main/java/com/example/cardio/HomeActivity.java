package com.example.cardio;

import static com.example.cardio.R.*;
import static com.example.cardio.R.id.helthtips_button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide Action Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(layout.activity_home);


        Button emergency_contact_page,hospital_type_button,helthtips_button,cardio_check_f,schemes_button,patient_full_report;

        emergency_contact_page = findViewById(id.emergency_contact_page);
        hospital_type_button = findViewById(id.hospital_type_button);
        helthtips_button = findViewById(id.helthtips_button);
        cardio_check_f=findViewById(id.cardio_check_f);
        schemes_button= findViewById(id.schemes_button);
        patient_full_report = findViewById(id.patient_full_report);

        database g = new database(this);
        SQLiteDatabase db = g.getReadableDatabase();

        //Dial Button
        emergency_contact_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent e_contact = new Intent(HomeActivity.this, Emergency_Contact.class);
                startActivity(e_contact);
            }
        });

        //Hospital type button
        hospital_type_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hospital_type = new Intent(HomeActivity.this,hospital_type_menu.class);
                startActivity(hospital_type);
            }
        });

        //helth tips button
        helthtips_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View view ) {
                Intent helth = new Intent(HomeActivity.this,InfoActivity.class);
                startActivity(helth);
            }
        });

        //Caedio check
        cardio_check_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View view ) {
                Intent cardo = new Intent(HomeActivity.this,patient_form.class);
                startActivity(cardo);
            }
        });

        //scheme_button
        schemes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View view ) {
                Intent scheme = new Intent(HomeActivity.this,shemes.class);
                startActivity(scheme);
            }
        });

        patient_full_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View view ) {
                Cursor m = g.getdata();
                if (m.getCount()==0){
                    Toast.makeText(HomeActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                }
                StringBuffer buffer = new StringBuffer();
                while (m.moveToNext()){
                    buffer.append("Report : " +m.getString(5)+"\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Patient Report History");
                builder.setMessage(buffer.toString());
                builder.show();


            }
        });


    }
}