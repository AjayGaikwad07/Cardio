package com.example.cardio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class New_User_Register extends AppCompatActivity {
    EditText Username,Password,RePassword,Name1;
    Button signup;
    DatabaseHelper MyDatabase;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);


        // hide Action Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_new_user_register);

        Name1 =findViewById(R.id.Name1);
        Username = findViewById(R.id.Username);
        Password = findViewById(R.id.Password);
        RePassword = findViewById(R.id.RePassword);
        signup = findViewById(R.id.signup);
        MyDatabase = new DatabaseHelper(this);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View view ) {

                String user = Username.getText().toString();
                String pass = Password.getText().toString();
                String repass = RePassword.getText().toString();
                String uname = Name1.getText().toString();

                if ( TextUtils.isEmpty(uname)) {
                    Name1.setError("Name Required");
                }

                if (TextUtils.isEmpty(pass)) {
                    Password.setError("Password Required");
                }

                if (TextUtils.isEmpty(repass)) {
                    RePassword.setError("Enter Password Again");
                }

                if (TextUtils.isEmpty(user))  {

                    if ( TextUtils.isEmpty(user)) {
                        Username.setError("Username Required");
                    }   else if (TextUtils.isEmpty(pass)) {
                        Password.setError("Password Required");
                    }

                } else if ( user.length()<8 || user.length()>18 ) {
                    Username.setError("It should be between 8 to 18 ");
                } else if (pass.length()<6 || pass.length()>12) {
                    Password.setError("Should be between 6 to 12 character");
                } else if (user.equals(pass)) {
                    Password.setError("Don't Username and Password same");
                } else if (TextUtils.isEmpty(repass)) {
                    RePassword.setError("Enter Password Again");

                }
                else {
                    if (pass.equals(repass)) {
                        Boolean checkuser = MyDatabase.checkusername(user);
                        if (checkuser == false) {
                            Boolean insert = MyDatabase.insertData(user,pass,uname);

                            if (insert == true) {
                                Toast.makeText(New_User_Register.this, "Registered Successfully ", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(New_User_Register.this, "Registered Failed", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Username.setError("User Already Exists");
                            Toast.makeText(New_User_Register.this, "User Already Exists", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        RePassword.setError("Password Not Match");

                    }
                }
            }
        });

        Name1.setFilters(new InputFilter[] { new InputFilter() {
            @Override
            public CharSequence filter( CharSequence source, int start, int end,
                                        Spanned dest, int dstart, int dend) {
                // Regular expression pattern to allow only alphabet characters
                String regex = "^[a-zA-Z\\s]+$";

                if (source.toString().matches(regex)) {
                     Name1.setError("Only Alphabetical");
                    return null; // Accept the input
                }
                return ""; // Reject the input
            }
        }});

    }
}