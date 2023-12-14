package com.example.cardio;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class patient_form extends AppCompatActivity {

    Button patient_history,patient_fill,button3;
    EditText patient_name,patient_age,patient_mobiel_number;
    RadioGroup radioGroup;
    RadioButton selectedRadioBtn,radioButton,radioButton2;
    TextView textView11;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        // hide Action Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_patient_form);

        database g = new database(this);
        SQLiteDatabase db = g.getReadableDatabase();

        patient_name= findViewById(R.id.patient_name);
        patient_age = findViewById(R.id.patient_age);
        patient_age.addTextChangedListener(ageTextWatcher);


        patient_mobiel_number = findViewById(R.id.patient_mobiel_number);

        textView11= findViewById(R.id.textView11);
        patient_fill = findViewById(R.id.patient_fill);
        patient_history= findViewById(R.id.patient_history);

        //Radio Button Group
        radioGroup = findViewById(R.id.radioGroup);
        radioButton = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);

        button3= findViewById(R.id.button3);

        // Patient name validation
        patient_name.setFilters(new InputFilter[] { new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                // Regular expression pattern to allow only alphabet characters
                String regex = "^[a-zA-Z\\s]+$";

                if (source.toString().matches(regex)) {

                    return null; // Accept the input
                }
                return ""; // Reject the input
            }
        }});

        // Mobiel number Validation

        patient_mobiel_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged ( CharSequence charSequence, int i, int i1, int i2 ) {

            }

            @Override
            public void onTextChanged ( CharSequence charSequence, int i, int i1, int i2 ) {

            }

            @Override
            public void afterTextChanged ( Editable editable ) {

                String mobileNumber = editable.toString().trim();

                if (isValidMobileNumber(mobileNumber)) {
                    // Valid mobile number
                    patient_mobiel_number.setError(null);
                } else {
                    // Invalid mobile number
                    patient_mobiel_number.setError("Invalid mobile number");
                }

            }
        });

        patient_fill.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick ( View view ) {


                String name = patient_name.getText().toString();
                String mobiel_number = patient_mobiel_number.getText().toString();
                String age = patient_age.getText().toString().trim();



                if  (patient_age.getText().toString().isEmpty()){
                    patient_age.setError("Cannot be Empty");
                }
                 if (patient_name.getText().toString().isEmpty()) {
                    patient_name.setError("Enter Name");

                } else if (patient_age.getText().toString().isEmpty() || Integer.parseInt(patient_age.getText().toString()) < 0) {
                    patient_age.setError("Cannot be Empty");


                } else if (!radioButton.isChecked() && !radioButton2.isChecked()) {
                    textView11.setError("Select Gender");

                } else {

                     //radio Button Male & Females
                     int ID = radioGroup.getCheckedRadioButtonId();
                     selectedRadioBtn = findViewById(ID);
                     textView11.setText("Gender: " + selectedRadioBtn.getText());

                /* if (name.equals("") || mobiel_number.equals("") || age.equals("") ||selectedRadioBtn.equals(null))
                {
                    Toast.makeText(patient_form.this, "Fill Details", Toast.LENGTH_SHORT).show();
                }
                else { */

                     int age1 = Integer.parseInt(age);

                     if (age1 >= 12 && age1 <= 120) {
                         // Age is valid
                         String rd = selectedRadioBtn.getText().toString();

                         Boolean i = g.insert_data(name, age, mobiel_number,rd);
                         if (i == true) {
                             Toast.makeText(patient_form.this, "Successful", Toast.LENGTH_SHORT).show();
                             Intent intent = new Intent(patient_form.this, Cardio_Check_form.class);
                             intent.putExtra("Patient_Name1", name);
                             intent.putExtra("Age", age);
                             intent.putExtra("ContactNo", mobiel_number);
                             intent.putExtra("TextViewValue", textView11.getText().toString());

                             startActivity(intent);

                         } else {
                             Toast.makeText(patient_form.this, "Not Successful", Toast.LENGTH_SHORT).show();
                         }
                     }
                 }
                 }
        });

        patient_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View view ) {
                Cursor t = g.getdata();
                if (t.getCount()==0) {
                    Toast.makeText(patient_form.this, "No Data Found", Toast.LENGTH_SHORT).show();
                }
                StringBuffer buffer = new StringBuffer();
                while (t.moveToNext()){

                    buffer.append("patient_name : "+t.getString(1)+"\n");
                    buffer.append("Gender : "+t.getString(4)+"\n");
                    buffer.append("patient_age : "+t.getString(2)+"\n");
                    buffer.append("patient_mobiel_number : "+t.getString(3)+"\n");

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(patient_form.this);
                builder.setCancelable(true);
                builder.setTitle("Patient Filled History");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View view ) {
                Intent bt = new Intent(patient_form.this,Cardio_Check_form.class);
                startActivity(bt);
            }
        });

    }


    private boolean isValidMobileNumber ( String mobileNumber ) {
        // Regex pattern to match a valid mobile number
       // String regexPattern = "^(\\+\\d{1,3}[- ]?)?\\d{10}$\\[6-9][0-9]{9}";
        String regexPattern = ("[6-9][0-9]{9}");
        return mobileNumber.matches(regexPattern);

    }

    TextWatcher ageTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // No action needed
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // No action needed
        }

        @Override
        public void afterTextChanged(Editable s) {
            String ageText = s.toString().trim();
            if (!ageText.isEmpty()) {
                int age = Integer.parseInt(ageText);
                if (age < 12 || age > 120) {
                    patient_age.setError("Age must be between 12 and 120");
                } else {
                    patient_age.setError(null);
                }
            }
        }
    };

}