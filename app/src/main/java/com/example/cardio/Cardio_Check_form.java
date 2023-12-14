package com.example.cardio;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Cardio_Check_form extends AppCompatActivity {

     Button Predict_Button,full_report_button;
     TextView detail_view_first,predict_result,Disease_name_predict,Disease_treatment;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        // hide Action Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        setContentView(R.layout.activity_cardio_check_form);

        database g = new database(this);

        ImageButton info_button1, info_button2, info_button3, info_button4, info_button5, info_button6, info_button7, info_button8;

        EditText chest_pain, heart_rate, slope, rest_ecg, cholestrol, rest_bps, fast_blood_sugar, oldpeak;

        detail_view_first = findViewById(R.id.detail_view_first); //Patient Personal Details
        predict_result = findViewById(R.id.predict_result);
        Disease_name_predict = findViewById(R.id.Disease_name_predict);
        Disease_treatment = findViewById(R.id.Disease_treatment);

        chest_pain = findViewById(R.id.chest_pain);
        heart_rate = findViewById(R.id.heart_rate);
        slope = findViewById(R.id.slope);
        rest_bps = findViewById(R.id.rest_bps);
        rest_ecg = findViewById(R.id.rest_ecg);
        cholestrol = findViewById(R.id.cholestrol);
        fast_blood_sugar = findViewById(R.id.fast_blood_sugar);
        oldpeak = findViewById(R.id.oldpeak);


        info_button1 = findViewById(R.id.info_Button1);
        info_button2 = findViewById(R.id.info_Button2);
        info_button3 = findViewById(R.id.info_Button3);
        info_button4 = findViewById(R.id.info_Button4);
        info_button5 = findViewById(R.id.info_Button5);
        info_button6 = findViewById(R.id.info_Button6);
        info_button7 = findViewById(R.id.info_Button7);
        info_button8 = findViewById(R.id.info_Button8);

        Predict_Button = findViewById(R.id.Predict_Button);
        full_report_button = findViewById(R.id.full_report_button);

        String p_name = getIntent().getStringExtra("Patient_Name1");
        String p_age = getIntent().getStringExtra("Age");
        String p_mobiel_number = getIntent().getStringExtra("ContactNo");
        String p_tv = getIntent().getStringExtra("TextViewValue");


        detail_view_first.setText("Patient Name:" + p_name + "\n" +
                        p_tv+"\n"+
                "Age: " + p_age + "\n" +
                "Mobiel No:" + p_mobiel_number
                 );

         predict_result.setHighlightColor(Color.RED);




        Predict_Button.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick ( View view ) {


                if  (chest_pain.getText().toString().isEmpty() || Integer.parseInt(chest_pain.getText().toString()) < 0) {
                    chest_pain.setError("Must be in 0-3 range");

                }  if (heart_rate.getText().toString().isEmpty() || Integer.parseInt(heart_rate.getText().toString()) < 0) {
                    heart_rate.setError("Must be in 60-160 range");

                }  if (slope.getText().toString().isEmpty() || Integer.parseInt(slope.getText().toString()) < 0) {
                    slope.setError("Must be in 0-2 range");

                }  if (rest_ecg.getText().toString().isEmpty() || Integer.parseInt(slope.getText().toString()) < 0) {
                    rest_ecg.setError("Must be in 0-2 range");

                }  if (cholestrol.getText().toString().isEmpty() || Integer.parseInt(cholestrol.getText().toString()) < 0) {
                    cholestrol.setError("Must be in 40-300 range");

                }  if (rest_bps.getText().toString().isEmpty() || Integer.parseInt(rest_bps.getText().toString()) < 0) {
                    rest_bps.setError("Must be in 85-180 range");

                }  if (fast_blood_sugar.getText().toString().isEmpty() || Integer.parseInt(fast_blood_sugar.getText().toString()) < 0) {
                    fast_blood_sugar.setError("Must be in 90mg/dl to 260mg/dl range");

                }  if (oldpeak.getText().toString().isEmpty() || Float.parseFloat(oldpeak.getText().toString()) < 0) {
                    oldpeak.setError(" Yes = 1 Or No = 0");

                } else  {
                    predict_result.setTextColor(Color.parseColor("#3949AB"));
                    Disease_treatment.setTextColor(Color.parseColor("#1B5E20"));
                    Disease_name_predict.setTextColor(Color.parseColor("#F44336"));

                }

                try {

                    String chest_pain2 = chest_pain.getText().toString();
                    int cp = Integer.parseInt(chest_pain2);
                    String heart_rate2 = heart_rate.getText().toString();
                    int hr = Integer.parseInt(heart_rate2);
                    String slope2 = slope.getText().toString();
                    int slope_n = Integer.parseInt(slope2);
                    String rest_ecg2 = rest_ecg.getText().toString();
                    int r_ecg = Integer.parseInt(rest_ecg2);
                    String cholestrol2 = cholestrol.getText().toString();
                    int chol = Integer.parseInt(cholestrol2);
                    String rest_bps2 = rest_bps.getText().toString();
                    int r_bp = Integer.parseInt(rest_bps2);
                    String fast_blood_sugar2 = fast_blood_sugar.getText().toString();
                    int fbs = Integer.parseInt(fast_blood_sugar2);
                    String oldpeak2 = oldpeak.getText().toString();
                    int oldpeak_n = Integer.parseInt(oldpeak2);


                    // Logistic Regration & Decision Tree

                     if (cp == 0 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 0 && chol >= 40 && chol <= 220 && r_bp >= 85 && r_bp <= 130 && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Not Presence Heart Disease");
                        Disease_name_predict.setText("Good Health");
                        Disease_treatment.setText("No treatment");
                    } else if (cp == 0 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 0 && chol >= 40 && chol <= 220 && r_bp >= 85 && r_bp <= 130 && fbs >= 116 && fbs <= 260 && oldpeak_n >= 0) {
                        predict_result.setText("Not Presence Heart Disease  ");
                        Disease_name_predict.setText("High Diabetes");
                        Disease_treatment.setText("Medicine");
                    } else if (cp == 0 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 0 && chol >= 221 && chol <= 300 && r_bp >= 85 && r_bp <= 130 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                        predict_result.setText(" Low Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate Very High Cholesterol ");
                        Disease_treatment.setText("Medicine");
                    } else if (cp == 0 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 0 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes");
                        Disease_treatment.setText("Medicine");
                    } else if (cp == 0 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 0 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Risk Heart Attack & Stroke");
                        Disease_name_predict.setText("Hypertensive Emergency high BP");
                        Disease_treatment.setText("Medicine");
                    } else if (cp == 0 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 40 && chol <= 220) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Cardiovascular disease");
                        Disease_treatment.setText("Medication,Surgery");
                        Disease_treatment.setText("Surgery");
                    } else if (cp == 0 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 0 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & High BP to chance CAD or Arrhythmias");
                        Disease_treatment.setText("Medicine");
                    } else if (cp == 0 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 40 && chol <= 220) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("High Diabetes && Cardiovascular disease");
                        Disease_treatment.setText("Medication,Surgery ");
                    } else if (cp == 0 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate Very High Cholesterol & Cardiovascular disease");
                        Disease_treatment.setText("Medication,Surgery");
                    } else if (cp == 0 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & Cardiovascular disease");
                        Disease_treatment.setText("Medication,Surgery");
                    } else if (cp == 0 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & Cardiovascular disease & High BP to chance CAD or Arrhythmias");
                        Disease_treatment.setText("Medication,Surgery");
                    } else if (cp == 0 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("High Diabetes & Cardiovascular disease & High BP to chance CAD or Arrhythmias");
                        Disease_treatment.setText("Medication,Surgery");
                    }
                    if (cp == 0 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 0 && chol >= 221 && chol <= 300 && r_bp >= 131 && r_bp <= 180 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_treatment.setText("Medicine");
                        Disease_name_predict.setText("Indicate Very High Cholesterol  & High BP ");
                    } else if (cp == 0 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate High Cholesterol & Cardiovascular disease & High BP to chance CAD or Arrhythmias");
                        Disease_treatment.setText("Medicine");
                    } else if (cp == 0 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 0 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText(" High Diabetes  & High BP to chance CAD or Arrhythmias");
                        Disease_treatment.setText("Medicine");
                    } else if (cp == 0 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Cardiovascular disease & High BP to chance CAD or Arrhythmias");
                        Disease_treatment.setText("Medication, Surgery");
                    } else if (cp == 0 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 1 && chol >= 40 && chol <= 220 && r_bp >= 85 && r_bp <= 130 && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Intrinsic myocardial disease ");
                        Disease_treatment.setText("Coronary artery bypass surgery");
                    } else if (cp == 0 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 1 && chol >= 40 && chol <= 220 && r_bp >= 85 && r_bp <= 130 && fbs >= 116 && fbs <= 260 && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("High Diabetes & Intrinsic myocardial disease");
                        Disease_treatment.setText("Coronary artery bypass surgery");
                    } else if (cp == 0 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 1 && chol >= 221 && chol <= 300 && r_bp >= 85 && r_bp <= 130 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate Very High Cholesterol & Intrinsic myocardial disease");
                        Disease_treatment.setText("Coronary artery bypass surgery");
                    } else if (cp == 0 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 1 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & Intrinsic myocardial disease");
                        Disease_treatment.setText("Coronary artery bypass surgery");
                    } else if (cp == 0 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 1 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Risk Heart Attack & Stroke");
                        Disease_name_predict.setText("Hypertensive Emergency high BP & Intrinsic myocardial disease");
                        Disease_treatment.setText("Coronary artery bypass surgery");
                    } else if (cp == 0 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 1 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & High BP to chance CAD or Arrhythmias & Intrinsic myocardial disease");
                        Disease_treatment.setText("Coronary artery bypass surgery");
                    } else if (cp == 0 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 40 && chol <= 220) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Cardiovascular disease & Intrinsic myocardial disease");
                        Disease_treatment.setText("Coronary artery bypass surgery");
                    } else if (cp == 0 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 40 && chol <= 220) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("High Diabetes && Cardiovascular disease & Intrinsic myocardial disease");
                        Disease_treatment.setText("Coronary artery bypass surgery");
                    } else if (cp == 0 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate Very High Cholesterol & Cardiovascular disease & Intrinsic myocardial disease");
                        Disease_treatment.setText("Coronary artery bypass surgery");
                    } else if (cp == 0 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & Cardiovascular disease & Intrinsic myocardial disease");
                        Disease_treatment.setText("Coronary artery bypass surgery");
                    } else if (cp == 0 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate High Cholesterol & Intrinsic myocardial disease & High Diabetes & Cardiovascular disease & High BP to chance CAD or Arrhythmias");
                        Disease_treatment.setText("Coronary artery bypass surgery");
                    } else if (cp == 0 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Intrinsic myocardial disease & High Diabetes & Cardiovascular disease & High BP to chance CAD or Arrhythmias");
                        Disease_treatment.setText("Coronary artery bypass surgery");
                    } else if (cp == 0 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 1 && chol >= 221 && chol <= 300 && r_bp >= 131 && r_bp <= 180 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate Very High Cholesterol & Chest pain chance Coronary artery disease & High BP & Intrinsic myocardial disease");
                        Disease_treatment.setText("Coronary artery bypass surgery");
                    } else if (cp == 0 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate High Cholesterol & Cardiovascular disease & High BP to chance CAD or Arrhythmias & Intrinsic myocardial disease");
                        Disease_treatment.setText("Coronary artery bypass surgery");
                    } else if (cp == 0 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 1 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText(" High Diabetes  & High BP to chance CAD or Arrhythmias & Intrinsic myocardial disease");
                        Disease_treatment.setText("Coronary artery bypass surgery");
                    } else if (cp == 0 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Cardiovascular disease & High BP to chance CAD or Arrhythmias & Intrinsic myocardial disease");
                        Disease_treatment.setText("Coronary artery bypass surgery");
                    } else if (cp == 1 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 0 && chol >= 40 && chol <= 220 && r_bp >= 85 && r_bp <= 130 && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Chest pain chance Coronary artery disease & Oher oragan show Normal");
                        Disease_treatment.setText("Angioplasty");
                    } else if (cp == 1 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 0 && chol >= 40 && chol <= 220 && r_bp >= 85 && r_bp <= 130 && fbs >= 116 && fbs <= 260 && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("High Diabetes & Chest pain chance Coronary artery disease");
                        Disease_treatment.setText("Angioplasty");
                    } else if (cp == 1 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 0 && chol >= 221 && chol <= 300 && r_bp >= 85 && r_bp <= 130 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate Very High Cholesterol & Chest pain chance Coronary artery disease");
                        Disease_treatment.setText("Angioplasty");
                    } else if (cp == 1 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 40 && chol <= 220) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Cardiovascular disease & Chest pain chance Coronary artery disease");
                        Disease_treatment.setText("Angioplasty & Surgery");
                    } else if (cp == 1 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 0 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & Chest pain chance Coronary artery disease");
                        Disease_treatment.setText("Angioplasty");
                    } else if (cp == 1 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 0 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Risk Heart Attack & Stroke");
                        Disease_name_predict.setText("Hypertensive Emergency high BP & Chest pain chance Coronary artery disease");
                        Disease_treatment.setText("Angioplasty");
                    } else if (cp == 1 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 0 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & High BP to chance CAD or Arrhythmias & Chest pain chance Coronary artery disease");
                        Disease_treatment.setText("Angioplasty");
                    } else if (cp == 1 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 40 && chol <= 220) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("High Diabetes && Cardiovascular disease & Chest pain chance Coronary artery disease");
                        Disease_treatment.setText("Angioplasty & Surgery");
                    } else if (cp == 1 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate Very High Cholesterol & Cardiovascular disease & Chest pain chance Coronary artery disease");
                        Disease_treatment.setText("Angioplasty & Surgery");
                    } else if (cp == 1 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & Cardiovascular disease & Chest pain chance Coronary artery disease");
                        Disease_treatment.setText("Angioplasty & Surgery");
                    } else if (cp == 1 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate High Cholesterol & Chest pain chance Coronary artery disease & High Diabetes & Cardiovascular disease & High BP to chance CAD or Arrhythmias");
                        Disease_treatment.setText("Angioplasty & Surgery");
                    } else if (cp == 1 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Chest pain chance Coronary artery disease & High Diabetes & Cardiovascular disease & High BP to chance CAD or Arrhythmias");
                        Disease_treatment.setText("Angioplasty & Surgery");
                    } else if (cp == 1 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 0 && chol >= 221 && chol <= 300 && r_bp >= 131 && r_bp <= 180 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate Very High Cholesterol & Chest pain chance Coronary artery disease & High BP");
                        Disease_treatment.setText("Angioplasty");
                    } else if (cp == 1 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate High Cholesterol & Cardiovascular disease & High BP to chance CAD or Arrhythmias & Chest pain chance Coronary artery disease");
                        Disease_treatment.setText("Angioplasty & Surgery");
                    } else if (cp == 1 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 0 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText(" High Diabetes  & High BP to chance CAD or Arrhythmias &  & Chest pain chance Coronary artery disease ");
                        Disease_treatment.setText("Angioplasty");
                    } else if (cp == 1 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Cardiovascular disease & High BP to chance CAD or Arrhythmias & Chest pain chance Coronary artery disease");
                        Disease_treatment.setText("Angioplasty & Surgery");
                    } else if (cp == 1 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 1 && chol >= 40 && chol <= 220 && r_bp >= 85 && r_bp <= 130 && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Chest pain chance Coronary artery disease & Intrinsic myocardial disease");
                        Disease_treatment.setText("Angioplasty & Coronary artery bypass surgery");
                    } else if (cp == 1 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 1 && chol >= 40 && chol <= 220 && r_bp >= 85 && r_bp <= 130 && fbs >= 116 && fbs <= 260 && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("High Diabetes & Chest pain chance Coronary artery disease & Intrinsic myocardial disease");
                        Disease_treatment.setText("Angioplasty & Coronary artery bypass surgery");
                    } else if (cp == 1 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 1 && chol >= 221 && chol <= 239 && r_bp >= 85 && r_bp <= 130 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate High Cholesterol & Chest pain chance Coronary artery disease & Intrinsic myocardial disease");
                        Disease_treatment.setText("Angioplasty & Coronary artery bypass surgery");
                    } else if (cp == 1 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 1 && chol >= 221 && chol <= 300 && r_bp >= 85 && r_bp <= 130 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate Very High Cholesterol & Chest pain chance Coronary artery disease & Intrinsic myocardial disease");
                        Disease_treatment.setText("Angioplasty & Coronary artery bypass surgery");
                    } else if (cp == 1 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 1 && chol >= 221 && chol <= 300 && r_bp >= 131 && r_bp <= 180 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate Very High Cholesterol & Chest pain chance Coronary artery disease & Intrinsic myocardial disease & High BP");
                        Disease_treatment.setText("Angioplasty & Coronary artery bypass surgery");
                    } else if (cp == 1 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 40 && chol <= 220) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Cardiovascular disease & Chest pain chance Coronary artery disease & Intrinsic myocardial disease");
                        Disease_treatment.setText("Angioplasty & Coronary artery bypass surgery");
                    } else if (cp == 1 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 1 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & Chest pain chance Coronary artery disease & Intrinsic myocardial disease");
                        Disease_treatment.setText("Angioplasty & Coronary artery bypass surgery");
                    } else if (cp == 1 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 1 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Risk Heart Attack & Stroke");
                        Disease_name_predict.setText("Hypertensive Emergency high BP & Chest pain chance Coronary artery disease & Intrinsic myocardial disease");
                        Disease_treatment.setText("Angioplasty & Coronary artery bypass surgery");
                    } else if (cp == 1 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 1 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & High BP to chance CAD or Arrhythmias & Chest pain chance Coronary artery disease & Intrinsic myocardial disease");
                        Disease_treatment.setText("Angioplasty & Coronary artery bypass surgery");
                    } else if (cp == 1 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 40 && chol <= 220) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("High Diabetes && Cardiovascular disease & Chest pain chance Coronary artery disease & Intrinsic myocardial disease");
                        Disease_treatment.setText("Angioplasty & Coronary artery bypass surgery");
                    } else if (cp == 1 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate Very High Cholesterol & Cardiovascular disease & Chest pain chance Coronary artery disease & Intrinsic myocardial disease");
                        Disease_treatment.setText("Angioplasty & Coronary artery bypass surgery");
                    } else if (cp == 1 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & Cardiovascular disease & Chest pain chance Coronary artery disease & Intrinsic myocardial disease");
                        Disease_treatment.setText("Angioplasty & Coronary artery bypass surgery");
                    } else if (cp == 1 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate High Cholesterol & Chest pain chance Coronary artery disease & High Diabetes & Cardiovascular disease & High BP to chance CAD or Arrhythmias & Intrinsic myocardial disease");
                        Disease_treatment.setText("Angioplasty & Coronary artery bypass surgery");
                    } else if (cp == 1 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Chest pain chance Coronary artery disease & High Diabetes & Cardiovascular disease & High BP to chance CAD or Arrhythmias & Intrinsic myocardial disease");
                        Disease_treatment.setText("Angioplasty & Coronary artery bypass surgery");
                    } else if (cp == 1 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate High Cholesterol & Cardiovascular disease & High BP to chance CAD or Arrhythmias & Chest pain chance Coronary artery disease & Intrinsic myocardial disease");
                        Disease_treatment.setText("Angioplasty & Coronary artery bypass surgery");
                    } else if (cp == 1 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 1 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText(" High Diabetes  & High BP to chance CAD or Arrhythmias & Chest pain chance Coronary artery disease & Intrinsic myocardial disease ");
                        Disease_treatment.setText("Angioplasty & Coronary artery bypass surgery");
                    } else if (cp == 1 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Cardiovascular disease & High BP to chance CAD or Arrhythmias & Intrinsic myocardial disease & Chest pain chance Coronary artery disease");
                        Disease_treatment.setText("Angioplasty & Coronary artery bypass surgery");
                    } else if (cp == 0 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 2 && chol >= 40 && chol <= 220 && r_bp >= 85 && r_bp <= 130 && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                    } else if (cp == 0 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 2 && chol >= 40 && chol <= 220 && r_bp >= 85 && r_bp <= 130 && fbs >= 116 && fbs <= 260 && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("High Diabetes& Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                    } else if (cp == 0 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 2 && chol >= 221 && chol <= 300 && r_bp >= 85 && r_bp <= 130 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate Very High Cholesterol& Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                    } else if (cp == 0 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 2 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes& Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                    } else if (cp == 0 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 2 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Risk Heart Attack & Stroke");
                        Disease_name_predict.setText("Hypertensive Emergency high BP& Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                    } else if (cp == 0 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 2 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & High BP to chance CAD or Arrhythmias& Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                    } else if (cp == 0 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 40 && chol <= 220) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Cardiovascular disease& Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                    } else if (cp == 0 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 40 && chol <= 220) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("High Diabetes && Cardiovascular disease& Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                    } else if (cp == 0 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate Very High Cholesterol & Cardiovascular disease& Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                    } else if (cp == 0 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & Cardiovascular disease& Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                    } else if (cp == 0 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate High Cholesterol& Left Ventricular hypertrophy(LVH) (arrhythmia) & High Diabetes & Cardiovascular disease & High BP to chance CAD or Arrhythmias");
                        Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                    } else if (cp == 0 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("High Diabetes & Cardiovascular disease & High BP to chance CAD or Arrhythmias");
                        Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                    } else if (cp == 0 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 2 && chol >= 221 && chol <= 300 && r_bp >= 131 && r_bp <= 180 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate Very High Cholesterol & Chest pain chance Coronary artery disease & High BP& Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                    } else if (cp == 0 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate High Cholesterol & Cardiovascular disease & High BP to chance CAD or Arrhythmias& Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                    } else if (cp == 0 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 2 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText(" High Diabetes  & High BP to chance CAD or Arrhythmias& Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                    } else if (cp == 0 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Cardiovascular disease & High BP to chance CAD or Arrhythmias  & Left Ventricular hypertrophy(LVH) (arrhythmia) ");
                        Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                    } else if (cp == 1 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 2 && chol >= 40 && chol <= 220 && r_bp >= 85 && r_bp <= 130 && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Chest pain chance Coronary artery disease & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        Disease_treatment.setText("Angioplasty & anti-arrhythmic drugs & surgery");
                    } else if (cp == 1 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 2 && chol >= 40 && chol <= 220 && r_bp >= 85 && r_bp <= 130 && fbs >= 116 && fbs <= 260 && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("High Diabetes & Chest pain chance Coronary artery disease & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        Disease_treatment.setText("Angioplasty & anti-arrhythmic drugs & surgery");
                    } else if (cp == 1 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 2 && chol >= 221 && chol <= 239 && r_bp >= 85 && r_bp <= 130 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate High Cholesterol & Chest pain chance Coronary artery disease & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        Disease_treatment.setText("Angioplasty & anti-arrhythmic drugs & surgery");
                    } else if (cp == 1 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 2 && chol >= 221 && chol <= 300 && r_bp >= 85 && r_bp <= 130 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate Very High Cholesterol & Chest pain chance Coronary artery disease  & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        Disease_treatment.setText("Angioplasty & anti-arrhythmic drugs & surgery");
                    } else if (cp == 1 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 2 && chol >= 221 && chol <= 300 && r_bp >= 131 && r_bp <= 180 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate Very High Cholesterol & Chest pain chance Coronary artery disease & Left Ventricular hypertrophy(LVH) (arrhythmia) & High BP");
                        Disease_treatment.setText("Angioplasty & anti-arrhythmic drugs & surgery");
                    } else if (cp == 1 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 40 && chol <= 220) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Cardiovascular disease & Chest pain chance Coronary artery disease & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        Disease_treatment.setText("Angioplasty & anti-arrhythmic drugs & surgery");
                    } else if (cp == 1 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 2 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & Chest pain chance Coronary artery disease & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        Disease_treatment.setText("Angioplasty & anti-arrhythmic drugs & surgery");
                    } else if (cp == 1 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 2 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Risk Heart Attack & Stroke");
                        Disease_name_predict.setText("Hypertensive Emergency high BP & Chest pain chance Coronary artery disease & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        Disease_treatment.setText("Angioplasty & anti-arrhythmic drugs & surgery");
                    } else if (cp == 1 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 2 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_treatment.setText("Angioplasty & anti-arrhythmic drugs & surgery");
                        Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & High BP to chance CAD or Arrhythmias & Chest pain chance Coronary artery disease & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                    } else if (cp == 1 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 40 && chol <= 220) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_name_predict.setText("High Diabetes && Cardiovascular disease & Chest pain chance Coronary artery disease & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                    } else if (cp == 1 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_treatment.setText("Angioplasty & anti-arrhythmic drugs & surgery");
                        Disease_name_predict.setText("Indicate Very High Cholesterol & Cardiovascular disease & Chest pain chance Coronary artery disease & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                    } else if (cp == 1 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_treatment.setText("Angioplasty & anti-arrhythmic drugs & surgery");
                        Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & Cardiovascular disease & Chest pain chance Coronary artery disease & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                    } else if (cp == 1 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_treatment.setText("Angioplasty & anti-arrhythmic drugs & surgery");
                        Disease_name_predict.setText("Indicate High Cholesterol & Chest pain chance Coronary artery disease & High Diabetes & Cardiovascular disease & High BP to chance CAD or Arrhythmias & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                    } else if (cp == 1 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_treatment.setText("Angioplasty & anti-arrhythmic drugs & surgery");
                        Disease_name_predict.setText("Chest pain chance Coronary artery disease & High Diabetes & Cardiovascular disease & High BP to chance CAD or Arrhythmias & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                    } else if (cp == 1 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_treatment.setText("Angioplasty & anti-arrhythmic drugs & surgery");
                        Disease_name_predict.setText("Indicate High Cholesterol & Cardiovascular disease & High BP to chance CAD or Arrhythmias & Chest pain chance Coronary artery disease & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                    } else if (cp == 1 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 2 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_treatment.setText("Angioplasty & anti-arrhythmic drugs & surgery");
                        Disease_name_predict.setText(" High Diabetes  & High BP to chance CAD or Arrhythmias & Chest pain chance Coronary artery disease & Left Ventricular hypertrophy(LVH) (arrhythmia) ");
                    } else if (cp == 1 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                        predict_result.setText("Chance of Presence Heart Disease");
                        Disease_treatment.setText("Angioplasty & anti-arrhythmic drugs & surgery");
                        Disease_name_predict.setText("Cardiovascular disease & High BP to chance CAD or Arrhythmias &  Arrhythmias& Left Ventricular hypertrophy(LVH) (arrhythmia)& Chest pain chance Coronary artery disease");
                    } else


                        //cp==2

                        if (cp == 2 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 0 && chol >= 40 && chol <= 220 && r_bp >= 85 && r_bp <= 130 && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Not Presence Heart Disease");
                            Disease_name_predict.setText("Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease)  & Oher oragan show Normal");
                            Disease_treatment.setText("Medicine");
                        } else if (cp == 2 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 0 && chol >= 40 && chol <= 220 && r_bp >= 85 && r_bp <= 130 && fbs >= 116 && fbs <= 260 && oldpeak_n >= 0) {
                            predict_result.setText("Not Presence Heart Disease");
                            Disease_name_predict.setText("High Diabetes & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease)");
                            Disease_treatment.setText("Medicine");
                        } else if (cp == 2 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 0 && chol >= 221 && chol <= 300 && r_bp >= 85 && r_bp <= 130 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                            predict_result.setText("Not Presence Heart Disease");
                            Disease_name_predict.setText("Indicate Very High Cholesterol & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease)");
                            Disease_treatment.setText("Medicine");
                        } else if (cp == 2 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 40 && chol <= 220) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Medicine");
                            Disease_name_predict.setText("Cardiovascular disease & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease)");
                        } else if (cp == 2 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 0 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Low Chance of Presence Heart Disease");
                            Disease_treatment.setText("Medicine");
                            Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease)");
                        } else if (cp == 2 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 0 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Risk Heart Attack & Stroke");
                            Disease_treatment.setText("Medicine");
                            Disease_name_predict.setText("Hypertensive Emergency high BP & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease)");
                        } else if (cp == 2 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 0 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Medicine");
                            Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & High BP to chance CAD or Arrhythmias & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease)");
                        } else if (cp == 2 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 40 && chol <= 220) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Medicine");
                            Disease_name_predict.setText("High Diabetes && Cardiovascular disease & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease)");
                        } else if (cp == 2 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Medicine");
                            Disease_name_predict.setText("Indicate Very High Cholesterol & Cardiovascular disease & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease)");
                        } else if (cp == 2 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Medicine");
                            Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & Cardiovascular disease & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease)");
                        } else if (cp == 2 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Medicine");
                            Disease_name_predict.setText("Indicate High Cholesterol & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & High Diabetes & Cardiovascular disease & High BP to chance CAD or Arrhythmias");
                        } else if (cp == 2 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Medication,Surgery");
                            Disease_name_predict.setText("Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & High Diabetes & Cardiovascular disease & High BP to chance CAD or Arrhythmias");
                        } else if (cp == 2 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 0 && chol >= 221 && chol <= 300 && r_bp >= 131 && r_bp <= 180 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                            predict_result.setText("Low Chance of Presence Heart Disease");
                            Disease_treatment.setText("Medicine");
                            Disease_name_predict.setText("Indicate Very High Cholesterol & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & High BP");
                        } else if (cp == 2 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Medication,Surgery");
                            Disease_name_predict.setText("Indicate High Cholesterol & Cardiovascular disease & High BP to chance CAD or Arrhythmias & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease)");
                        } else if (cp == 2 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 0 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Low Chance of Presence Heart Disease");
                            Disease_treatment.setText("Medicine");
                            Disease_name_predict.setText(" High Diabetes  & High BP to chance CAD or Arrhythmias &  & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) ");
                        } else if (cp == 2 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Medication,Surgery");
                            Disease_name_predict.setText("Cardiovascular disease & High BP to chance CAD or Arrhythmias & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease)");
                        } else if (cp == 2 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 1 && chol >= 40 && chol <= 220 && r_bp >= 85 && r_bp <= 130 && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Coronary artery bypass surgery");
                            Disease_name_predict.setText("Non anginal is most noncardic chest pain (gastro-esophageal reflux disease) & Intrinsic myocardial disease");
                        } else if (cp == 2 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 1 && chol >= 40 && chol <= 220 && r_bp >= 85 && r_bp <= 130 && fbs >= 116 && fbs <= 260 && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Coronary artery bypass surgery");
                            Disease_name_predict.setText("High Diabetes & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & Intrinsic myocardial disease");
                        } else if (cp == 2 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 1 && chol >= 221 && chol <= 239 && r_bp >= 85 && r_bp <= 130 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Coronary artery bypass surgery");
                            Disease_name_predict.setText("Indicate High Cholesterol & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & Intrinsic myocardial disease");
                        } else if (cp == 2 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 1 && chol >= 221 && chol <= 300 && r_bp >= 85 && r_bp <= 130 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Coronary artery bypass surgery");
                            Disease_name_predict.setText("Indicate Very High Cholesterol & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & Intrinsic myocardial disease");
                        } else if (cp == 2 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 1 && chol >= 221 && chol <= 300 && r_bp >= 131 && r_bp <= 180 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Coronary artery bypass surgery");
                            Disease_name_predict.setText("Indicate Very High Cholesterol & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & Intrinsic myocardial disease & High BP");
                        } else if (cp == 2 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 40 && chol <= 220) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Coronary artery bypass surgery");
                            Disease_name_predict.setText("Cardiovascular disease & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & Intrinsic myocardial disease");
                        } else if (cp == 2 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 1 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Coronary artery bypass surgery");
                            Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & Intrinsic myocardial disease");
                        } else if (cp == 2 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 1 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Risk Heart Attack & Stroke");
                            Disease_treatment.setText("Coronary artery bypass surgery");
                            Disease_name_predict.setText("Hypertensive Emergency high BP & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & Intrinsic myocardial disease");
                        } else if (cp == 2 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 1 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Coronary artery bypass surgery");
                            Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & High BP to chance CAD or Arrhythmias & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & Intrinsic myocardial disease");
                        } else if (cp == 2 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 40 && chol <= 220) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Coronary artery bypass surgery");
                            Disease_name_predict.setText("High Diabetes && Cardiovascular disease & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & Intrinsic myocardial disease");
                        } else if (cp == 2 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Coronary artery bypass surgery");
                            Disease_name_predict.setText("Indicate Very High Cholesterol & Cardiovascular disease & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & Intrinsic myocardial disease");
                        } else if (cp == 2 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Coronary artery bypass surgery");
                            Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & Cardiovascular disease & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & Intrinsic myocardial disease");
                        } else if (cp == 2 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Coronary artery bypass surgery");
                            Disease_name_predict.setText("Indicate High Cholesterol & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & High Diabetes & Cardiovascular disease & High BP to chance CAD or Arrhythmias & Intrinsic myocardial disease");
                        } else if (cp == 2 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Coronary artery bypass surgery");
                            Disease_name_predict.setText("Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & High Diabetes & Cardiovascular disease & High BP to chance CAD or Arrhythmias & Intrinsic myocardial disease");
                        } else if (cp == 2 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Coronary artery bypass surgery");
                            Disease_name_predict.setText("Indicate High Cholesterol & Cardiovascular disease & High BP to chance CAD or Arrhythmias & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & Intrinsic myocardial disease");
                        } else if (cp == 2 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 1 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Coronary artery bypass surgery");
                            Disease_name_predict.setText(" High Diabetes  & High BP to chance CAD or Arrhythmias & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & Intrinsic myocardial disease ");
                        } else if (cp == 2 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Coronary artery bypass surgery");
                            Disease_name_predict.setText("Cardiovascular disease & High BP to chance CAD or Arrhythmias & Intrinsic myocardial disease & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease)");
                        } else if (cp == 2 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 2 && chol >= 40 && chol <= 220 && r_bp >= 85 && r_bp <= 130 && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        } else if (cp == 2 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 2 && chol >= 40 && chol <= 220 && r_bp >= 85 && r_bp <= 130 && fbs >= 116 && fbs <= 260 && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("High Diabetes & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        } else if (cp == 2 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 2 && chol >= 221 && chol <= 239 && r_bp >= 85 && r_bp <= 130 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("Indicate High Cholesterol & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        } else if (cp == 2 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 2 && chol >= 221 && chol <= 300 && r_bp >= 85 && r_bp <= 130 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("Indicate Very High Cholesterol & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease)  & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        } else if (cp == 2 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 2 && chol >= 221 && chol <= 300 && r_bp >= 131 && r_bp <= 180 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("Indicate Very High Cholesterol & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & Left Ventricular hypertrophy(LVH) (arrhythmia) & High BP");
                        } else if (cp == 2 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 40 && chol <= 220) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("Cardiovascular disease & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        } else if (cp == 2 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 2 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        } else if (cp == 2 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 2 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Risk Heart Attack & Stroke");
                            Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("Hypertensive Emergency high BP & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        } else if (cp == 2 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 2 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & High BP to chance CAD or Arrhythmias & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        } else if (cp == 2 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 40 && chol <= 220) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("High Diabetes && Cardiovascular disease & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        } else if (cp == 2 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("Indicate Very High Cholesterol & Cardiovascular disease & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        } else if (cp == 2 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & Cardiovascular disease & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        } else if (cp == 2 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("Indicate High Cholesterol & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & High Diabetes & Cardiovascular disease & High BP to chance CAD or Arrhythmias & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        } else if (cp == 2 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_name_predict.setText("Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & High Diabetes & Cardiovascular disease & High BP to chance CAD or Arrhythmias & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        } else if (cp == 2 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_name_predict.setText("Indicate High Cholesterol & Cardiovascular disease & High BP to chance CAD or Arrhythmias & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        } else if (cp == 2 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 2 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText(" High Diabetes  & High BP to chance CAD or Arrhythmias & Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & Left Ventricular hypertrophy(LVH) (arrhythmia) ");
                        } else if (cp == 2 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("Cardiovascular disease & High BP to chance CAD or Arrhythmias &  Arrhythmias& Left Ventricular hypertrophy(LVH) (arrhythmia)& Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease)");
                        } else if (cp == 3 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 0 && chol >= 40 && chol <= 220 && r_bp >= 85 && r_bp <= 130 && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Not Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty");
                            Disease_name_predict.setText("Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea)  & Oher oragan show Normal");
                        } else if (cp == 3 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 0 && chol >= 40 && chol <= 220 && r_bp >= 85 && r_bp <= 130 && fbs >= 116 && fbs <= 260 && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty");
                            Disease_name_predict.setText("High Diabetes &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea)");
                        } else if (cp == 3 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 0 && chol >= 221 && chol <= 300 && r_bp >= 85 && r_bp <= 130 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty");
                            Disease_name_predict.setText("Indicate Very High Cholesterol &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea)");
                        } else if (cp == 3 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 40 && chol <= 220) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty");
                            Disease_name_predict.setText("Cardiovascular disease &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea)");
                        } else if (cp == 3 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 0 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty");
                            Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea)");
                        } else if (cp == 3 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 0 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Risk Heart Attack & Stroke");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty");
                            Disease_name_predict.setText("Hypertensive Emergency high BP &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea)");
                        } else if (cp == 3 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 0 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty");
                            Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & High BP to chance CAD or Arrhythmias &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea)");
                        } else if (cp == 3 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 40 && chol <= 220) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty");
                            Disease_name_predict.setText("High Diabetes && Cardiovascular disease &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea)");
                        } else if (cp == 3 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty");
                            Disease_name_predict.setText("Indicate Very High Cholesterol & Cardiovascular disease &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea)");
                        } else if (cp == 3 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty");
                            Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & Cardiovascular disease &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea)");
                        } else if (cp == 3 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty");
                            Disease_name_predict.setText("Indicate High Cholesterol &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & High Diabetes & Cardiovascular disease & High BP to chance CAD or Arrhythmias");
                        } else if (cp == 3 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty");
                            Disease_name_predict.setText("Non anginal is most noncardic chest pain (gastro-esophageal  reflux disease) & High Diabetes & Cardiovascular disease & High BP to chance CAD or Arrhythmias");
                        } else if (cp == 3 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 0 && chol >= 221 && chol <= 300 && r_bp >= 131 && r_bp <= 180 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty");
                            Disease_name_predict.setText("Indicate Very High Cholesterol &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & High BP");
                        } else if (cp == 3 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty");
                            Disease_name_predict.setText("Indicate High Cholesterol & Cardiovascular disease & High BP to chance CAD or Arrhythmias &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea)");
                        } else if (cp == 3 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 0 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty");
                            Disease_name_predict.setText(" High Diabetes  & High BP to chance CAD or Arrhythmias &  &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) ");
                        } else if (cp == 3 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 0 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty");
                            Disease_name_predict.setText("Cardiovascular disease & High BP to chance CAD or Arrhythmias &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea)");
                        } else if (cp == 3 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 1 && chol >= 40 && chol <= 220 && r_bp >= 85 && r_bp <= 130 && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & Coronary artery bypass surgery");
                            Disease_name_predict.setText("Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) ex: Dyspnea) & Intrinsic myocardial disease");
                        } else if (cp == 3 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 1 && chol >= 40 && chol <= 220 && r_bp >= 85 && r_bp <= 130 && fbs >= 116 && fbs <= 260 && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & Coronary artery bypass surgery");
                            Disease_name_predict.setText("High Diabetes &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & Intrinsic myocardial disease");
                        } else if (cp == 3 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 1 && chol >= 221 && chol <= 239 && r_bp >= 85 && r_bp <= 130 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & Coronary artery bypass surgery");
                            Disease_name_predict.setText("Indicate High Cholesterol &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & Intrinsic myocardial disease");
                        } else if (cp == 3 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 1 && chol >= 221 && chol <= 300 && r_bp >= 85 && r_bp <= 130 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & Coronary artery bypass surgery");
                            Disease_name_predict.setText("Indicate Very High Cholesterol &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & Intrinsic myocardial disease");
                        } else if (cp == 3 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 1 && chol >= 221 && chol <= 300 && r_bp >= 131 && r_bp <= 180 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & Coronary artery bypass surgery");
                            Disease_name_predict.setText("Indicate Very High Cholesterol &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & Intrinsic myocardial disease & High BP");
                        } else if (cp == 3 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 40 && chol <= 220) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & Coronary artery bypass surgery");
                            Disease_name_predict.setText("Cardiovascular disease &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & Intrinsic myocardial disease");
                        } else if (cp == 3 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 1 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & Coronary artery bypass surgery");
                            Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & Intrinsic myocardial disease");
                        } else if (cp == 3 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 1 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & Coronary artery bypass surgery");
                            Disease_name_predict.setText("Hypertensive Emergency high BP &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & Intrinsic myocardial disease");
                        } else if (cp == 3 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 1 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & Coronary artery bypass surgery");
                            Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & High BP to chance CAD or Arrhythmias &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & Intrinsic myocardial disease");
                        } else if (cp == 3 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 40 && chol <= 220) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & Coronary artery bypass surgery");
                            Disease_name_predict.setText("High Diabetes && Cardiovascular disease &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & Intrinsic myocardial disease");
                        } else if (cp == 3 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & Coronary artery bypass surgery");
                            Disease_name_predict.setText("Indicate Very High Cholesterol & Cardiovascular disease &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & Intrinsic myocardial disease");
                        } else if (cp == 3 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & Coronary artery bypass surgery");
                            Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & Cardiovascular disease &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & Intrinsic myocardial disease");
                        } else if (cp == 3 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & Coronary artery bypass surgery");
                            Disease_name_predict.setText("Indicate High Cholesterol &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & High Diabetes & Cardiovascular disease & High BP to chance CAD or Arrhythmias & Intrinsic myocardial disease");
                        } else if (cp == 3 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & Coronary artery bypass surgery");
                            Disease_name_predict.setText("Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & High Diabetes & Cardiovascular disease & High BP to chance CAD or Arrhythmias & Intrinsic myocardial disease");
                        } else if (cp == 3 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & Coronary artery bypass surgery");
                            Disease_name_predict.setText("Indicate High Cholesterol & Cardiovascular disease & High BP to chance CAD or Arrhythmias &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & Intrinsic myocardial disease");
                        } else if (cp == 3 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 1 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & Coronary artery bypass surgery");
                            Disease_name_predict.setText(" High Diabetes  & High BP to chance CAD or Arrhythmias &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & Intrinsic myocardial disease ");
                        } else if (cp == 3 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 1 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & Coronary artery bypass surgery");
                            Disease_name_predict.setText("Cardiovascular disease & High BP to chance CAD or Arrhythmias & Intrinsic myocardial disease &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea)");
                        } else if (cp == 3 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 2 && chol >= 40 && chol <= 220 && r_bp >= 85 && r_bp <= 130 && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        } else if (cp == 3 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 2 && chol >= 40 && chol <= 220 && r_bp >= 85 && r_bp <= 130 && fbs >= 116 && fbs <= 260 && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("High Diabetes &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        } else if (cp == 3 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 2 && chol >= 221 && chol <= 239 && r_bp >= 85 && r_bp <= 130 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("Indicate High Cholesterol &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        } else if (cp == 3 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 2 && chol >= 221 && chol <= 300 && r_bp >= 85 && r_bp <= 130 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("Indicate Very High Cholesterol &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea)  & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        } else if (cp == 3 && hr >= 60 && hr <= 100 && slope_n >= 0 && r_ecg == 2 && chol >= 221 && chol <= 300 && r_bp >= 131 && r_bp <= 180 && fbs >= 90 && fbs <= 115 && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("Indicate Very High Cholesterol &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & Left Ventricular hypertrophy(LVH) (arrhythmia) & High BP");
                        } else if (cp == 3 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 40 && chol <= 220) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("Cardiovascular disease &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        } else if (cp == 3 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 2 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        } else if (cp == 3 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 2 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Risk Heart Attack & Stroke");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("Hypertensive Emergency high BP &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        } else if (cp == 3 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 2 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & High BP to chance CAD or Arrhythmias &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        } else if (cp == 3 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 40 && chol <= 220) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("High Diabetes && Cardiovascular disease &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        } else if (cp == 3 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("Indicate Very High Cholesterol & Cardiovascular disease &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        } else if (cp == 3 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 221 && chol <= 300) && (r_bp >= 85 && r_bp <= 130) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("Indicate High Cholesterol & High Diabetes & Cardiovascular disease &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        } else if (cp == 3 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("Indicate High Cholesterol &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & High Diabetes & Cardiovascular disease & High BP to chance CAD or Arrhythmias & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        } else if (cp == 3 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & High Diabetes & Cardiovascular disease & High BP to chance CAD or Arrhythmias & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        } else if (cp == 3 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 221 && chol <= 300) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("Indicate High Cholesterol & Cardiovascular disease & High BP to chance CAD or Arrhythmias &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & Left Ventricular hypertrophy(LVH) (arrhythmia)");
                        } else if (cp == 3 && (hr >= 60 && hr <= 100) && slope_n >= 0 && r_ecg == 2 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 116 && fbs <= 260) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText(" High Diabetes  & High BP to chance CAD or Arrhythmias &Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea) & Left Ventricular hypertrophy(LVH) (arrhythmia) ");
                        } else if (cp == 3 && (hr >= 101 && hr <= 160) && slope_n >= 0 && r_ecg == 2 && (chol >= 40 && chol <= 220) && (r_bp >= 131 && r_bp <= 180) && (fbs >= 90 && fbs <= 115) && oldpeak_n >= 0) {
                            predict_result.setText("Chance of Presence Heart Disease");
                            Disease_treatment.setText("Aspirin(anti-platelet therapy & Balloon angioplasty & anti-arrhythmic drugs & surgery");
                            Disease_name_predict.setText("Cardiovascular disease & High BP to chance CAD or Arrhythmias &  Arrhythmias& Left Ventricular hypertrophy(LVH) (arrhythmia)&Silent mycordial ischemia (reduce oxygen in blood flow to the heart ex: Dyspnea)");
                        } else {

                            Toast.makeText(Cardio_Check_form.this, " Check Inputs ", Toast.LENGTH_SHORT).show();
                        }

                    return;


                } catch (Exception exception) {

                    Toast.makeText(Cardio_Check_form.this, "Fill Data", Toast.LENGTH_SHORT).show();
                }

            }
        });

           chest_pain.addTextChangedListener(new TextWatcher() {

               @Override
               public void beforeTextChanged ( CharSequence charSequence, int i, int i1, int i2 ) {
               }
               @Override
               public void onTextChanged ( CharSequence charSequence, int i, int i1, int i2 ) {
               }
               @Override
               public void afterTextChanged ( Editable editable1 ) {
                   String CRate = editable1.toString().trim();
                   if (isValidCR(CRate)) {
                       
                       chest_pain.setError(null);
                   } else {
                      
                       chest_pain.setError("Must be 0 to 3");
                   }
               }
               private boolean isValidCR ( String cRate ) {
                   String regexPattern = "^[0-3]$";
                   return cRate.matches(regexPattern);
               }

           });

           // Slope
          slope.addTextChangedListener(new TextWatcher() {
              @Override
              public void beforeTextChanged ( CharSequence charSequence, int i, int i1, int i2 ) {
              }
              @Override
              public void onTextChanged ( CharSequence charSequence, int i, int i1, int i2 ) {
              }
              @Override
              public void afterTextChanged ( Editable editable3 ) {
                  String SlopeRate = editable3.toString().trim();
                  if (isValidSlope(SlopeRate)) {
                      
                      slope.setError(null);
                  } else {
                     
                      slope.setError("Must be 0 to 2");
                  }
              }
              private boolean isValidSlope ( String slopeRate ) {
                  String regexPattern = "^[0-2]$";
                  return slopeRate.matches(regexPattern);
              }
          });


         //
        rest_bps.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged ( CharSequence charSequence, int i, int i1, int i2 ) {
            }
            @Override
            public void onTextChanged ( CharSequence charSequence, int i, int i1, int i2 ) {
            }
            @Override
            public void afterTextChanged ( Editable editable4 ) {
                String BPRate = editable4.toString().trim();
                if (isValidBP(BPRate)) {
                    
                    rest_bps.setError(null);
                } else {
                   
                    rest_bps.setError("Must be 85 to 180");
                }
            }
            private boolean isValidBP ( String BPRate ) {
                String regexPattern = "^(?:8[5-9]|[9-9][0-9]|1[0-7][0-9]|180)$";
                return BPRate.matches(regexPattern);
            }
        });




        rest_ecg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged ( CharSequence charSequence, int i, int i1, int i2 ) {
            }
            @Override
            public void onTextChanged ( CharSequence charSequence, int i, int i1, int i2 ) {
            }
            @Override
            public void afterTextChanged ( Editable editable5 ) {
                String ECGRate = editable5.toString().trim();
                if (isValidECG(ECGRate)) {
                    
                    rest_ecg.setError(null);
                } else {
                   
                    rest_ecg.setError("Must be 0 to 2");
                }
            }
            private boolean isValidECG ( String ECGRate ) {
                String regexPattern = "^[0-2]$";
                return ECGRate.matches(regexPattern);
            }
        });

        cholestrol.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged ( CharSequence charSequence, int i, int i1, int i2 ) {
            }
            @Override
            public void onTextChanged ( CharSequence charSequence, int i, int i1, int i2 ) {
            }
            @Override
            public void afterTextChanged ( Editable editable6 ) {
                String CholRate = editable6.toString().trim();
                if (isValidCholestrol(CholRate)) {
                    
                    cholestrol.setError(null);
                } else {
                   
                    cholestrol.setError("Must be 40 to 300");
                }
            }
            private boolean isValidCholestrol ( String CholRate ) {
                String regexPattern = "^(?:[4-9][0-9]|[1-2][0-9]{2}|300)$";
                return CholRate.matches(regexPattern);
            }
        });

        fast_blood_sugar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged ( CharSequence charSequence, int i, int i1, int i2 ) {
            }
            @Override
            public void onTextChanged ( CharSequence charSequence, int i, int i1, int i2 ) {
            }
            @Override
            public void afterTextChanged ( Editable editable6 ) {
                String BSugarRate = editable6.toString().trim();
                if (isValidB_sugar(BSugarRate)) {
                    
                    fast_blood_sugar.setError(null);
                } else {
                   
                    fast_blood_sugar.setError("Must be 90 to 260");
                }
            }
            private boolean isValidB_sugar ( String BSugarRate ) {
                String regexPattern = "^(?:9[0-9]|[1-2][0-5][0-9]|260)$";
                return BSugarRate.matches(regexPattern);
            }
        });

        oldpeak.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged ( CharSequence charSequence, int i, int i1, int i2 ) {
            }
            @Override
            public void onTextChanged ( CharSequence charSequence, int i, int i1, int i2 ) {
            }
            @Override
            public void afterTextChanged ( Editable editable5 ) {
                String OldpeackRate = editable5.toString().trim();
                if (isValidOldpeack(OldpeackRate)) {
                    
                    oldpeak.setError(null);
                } else {
                   
                    oldpeak.setError("Must be 0 to 1");
                }
            }
            private boolean isValidOldpeack ( String OldpeackRate ) {
                String regexPattern = "^[0-1]$";
                return OldpeackRate.matches(regexPattern);
            }
        });

        heart_rate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged ( CharSequence charSequence, int i, int i1, int i2 ) {

            }

            @Override
            public void onTextChanged ( CharSequence charSequence, int i, int i1, int i2 ) {

            }

            @Override
            public void afterTextChanged ( Editable editableo ) {
                String HRate = editableo.toString().trim();

                if (isValidHR(HRate)) {
                    
                    heart_rate.setError(null);
                } else {
                    
                    heart_rate.setError("Must be 60 to 160");
                }
            }
            private boolean isValidHR ( String hRate ) {
                String regexPattern = "^(?:6[0-9]|[7-9][0-9]|1[0-5][0-9]|160)$";
                return hRate.matches(regexPattern);
            }
        });
        


        full_report_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View view ) {

                String chest_pain2 = chest_pain.getText().toString();
                String heart_rate2 = heart_rate.getText().toString();
                String slope2 = slope.getText().toString();
                String rest_ecg2 = rest_ecg.getText().toString();
                String cholestrol2 = cholestrol.getText().toString();
                String rest_bps2 = rest_bps.getText().toString();
                String fast_blood_sugar2 = fast_blood_sugar.getText().toString();
                String oldpeak2 = oldpeak.getText().toString();


                Intent Pass = new Intent(Cardio_Check_form.this, treatment_suggest.class);


                Pass.putExtra("chest_pain3", chest_pain2);
                Pass.putExtra("heart_rate3", heart_rate2);
                Pass.putExtra("slope3", slope2);
                Pass.putExtra("rest_ecg3", rest_ecg2);
                Pass.putExtra("cholestrol3", cholestrol2);
                Pass.putExtra("rest_bps3", rest_bps2);
                Pass.putExtra("fast_blood_sugar3", fast_blood_sugar2);
                Pass.putExtra("oldpeak3", oldpeak2);
                Pass.putExtra("TV2", detail_view_first.getText().toString());
                Pass.putExtra("TVPD", predict_result.getText().toString());
                Pass.putExtra("DTV", Disease_name_predict.getText().toString());
                Pass.putExtra("DT", Disease_treatment.getText().toString());
                Toast.makeText(Cardio_Check_form.this, "Report Genrated", Toast.LENGTH_SHORT).show();
                startActivity(Pass);

            }
        });

        info_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View view ) {
                String info = "It should display the type of chest-pain experienced by the individual using the following format :\n" +
                        "0 = typical angina\n" +
                        "1 = atypical angina\n" +
                        "2 = non — anginal pain\n" +
                        "3 = asymptotic";
                infoDialog("Chest Pain Type:", info);
            }
        });

        info_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View view ) {
                String info = "It should display the max heart rate achieved by an individual between 60 to 160.";
                infoDialog("Max heart rate:", info);
            }
        });

        info_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View view ) {
                String info = "Peak exercise ST segment :\n" +
                        "0 = upsloping\n" +
                        "1 = flat\n" +
                        "2 = downsloping";
                infoDialog("Exercise ST:", info);
            }
        });

        info_button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View view ) {
                String info = "It should display resting electrocardiographic results\n" +
                        "0 = normal\n" +
                        "1 = having ST-T wave abnormality\n" +
                        "2 = left ventricular hyperthrophy";
                infoDialog("Resting ECG:", info);
            }
        });

        info_button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View view ) {
                String info = "It should display the serum cholesterol in mg/dl (unit) between 40mg/dl to 300mg/dl";
                infoDialog("Cholestrol:", info);
            }
        });

        info_button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View view ) {
                String info = "It should display the resting blood pressure value of an individual in mmHg (unit) between 85mmHg to 180mmHg";
                infoDialog("Resting Blood Pressure:", info);
            }
        });

        info_button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View view ) {
                String info = "It compares the fasting blood sugar value of an individual with 90mg/dl to 260mg/dl";

                infoDialog("Fasting Blood Sugar:", info);
            }
        });

        info_button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View view ) {
                String info = "ST depression induced by exercise relative to rest, ST Depression No = 0 Or Yes = 1";
                infoDialog("ST depression:", info);
            }
        });
    }


    private void infoDialog(String i,String string){
    Dialog dialog;
    dialog = new Dialog(Cardio_Check_form.this);

    dialog.setContentView(R.layout.info_dialog);
    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    ImageView close = dialog.findViewById(R.id.closeDialog);
    TextView nameDialog = dialog.findViewById(R.id.nameDialog);
    TextView infoDialog = dialog.findViewById(R.id.infoDialog);

    nameDialog.setText(i);
    infoDialog.setText(string);

    close.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick ( View view ) {
            dialog.cancel();
        }
    });
            dialog.show();

}

}