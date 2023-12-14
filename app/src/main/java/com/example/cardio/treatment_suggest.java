package com.example.cardio;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class treatment_suggest extends AppCompatActivity {
    TextView detail_view,detail_view2;
    private int tokenCounter;
    Button button_save,button_go_menu;




    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);

        // hide Action Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_treatment_suggest);

        detail_view = findViewById(R.id.detail_view);
        button_save= findViewById(R.id.button_save);
        button_go_menu = findViewById(R.id.button_go_menu);

        database t = new database(this);
        SQLiteDatabase db = t.getWritableDatabase();


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String currentDate = dateFormat.format(new Date());

        SimpleDateFormat timeformet = new SimpleDateFormat("HH:mm:ss",Locale.getDefault());
        String currentTime = timeformet.format(new Date());

        generateTokenNumber();


        String p_tv2 =getIntent().getStringExtra("TV2");
        String p_tv3 = getIntent().getStringExtra("TVPD");
        String p_tv4 = getIntent().getStringExtra("DTV");
        String p_tv5 = getIntent().getStringExtra("DT");

        String p_chest_pain2 = getIntent().getStringExtra("chest_pain3");
        String p_heart_rate2 = getIntent().getStringExtra("heart_rate3");
        String p_slope2 = getIntent().getStringExtra("slope3");
        String p_rest_ecg2 = getIntent().getStringExtra("rest_ecg3");
        String p_cholestrol2= getIntent().getStringExtra("cholestrol3");
        String p_rest_bps2= getIntent().getStringExtra("rest_bps3");
        String p_fast_blood_sugar2 = getIntent().getStringExtra("fast_blood_sugar3");
        String p_oldpeak2 = getIntent().getStringExtra("oldpeak3");


        detail_view.setText(


                "Date : "+currentDate+ "  \n"+ "Time :" +currentTime+"\n"+

                 "\n"+p_tv2+"\n"+
                "\n"+
                "Filled Details \n"+
                "             \n"+
                "Chest pain type :"+p_chest_pain2+"\n"+
                "Heart rate :"+p_heart_rate2+"\n"+
                "Slope :"+p_slope2+"\n"+
                "Rest ECG :"+p_rest_ecg2+"\n"+
                "Cholesterol :"+p_cholestrol2+"\n"+
                "Resting Blood Pressure :"+p_rest_bps2+""+"\n"+
                "Fasting blood pressure :"+ p_fast_blood_sugar2+"\n"+
                "Old peak :"+p_oldpeak2+"\n"+
                "             \n"+
                "Predict Result :\n"+
                p_tv3+"\n"+
                "          \n" +
                "Predicted Disease :\n" +
                p_tv4+"\n"+
                "\n"+
                "Disease Treatments Type : \n"+
                p_tv5+"\n"+
                "=================================");

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View view ) {

                String tv_report = detail_view.getText().toString();

                Boolean i = t.insert_data(tv_report);
                if (i==true){
                    Toast.makeText(treatment_suggest.this, "Saved", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(treatment_suggest.this, " Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_go_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View view ) {
                Intent button_go_menu = new Intent(treatment_suggest.this ,HomeActivity.class);
                startActivity(button_go_menu);
            }
        });

    }

    private void generateTokenNumber () {
        // Increment the token counter
        tokenCounter++;
    }


}