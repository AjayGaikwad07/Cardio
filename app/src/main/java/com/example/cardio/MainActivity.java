package com.example.cardio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Username1, Password1;
    Button Login1;
    DatabaseHelper MyDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide Action Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        Button guest_login, register_new;

        Username1 = findViewById(R.id.Username1);
        Password1 = findViewById(R.id.Password1);
        Login1 = findViewById(R.id.Login1);
        MyDatabase = new DatabaseHelper(this);

        guest_login = findViewById(R.id.guest_login);
        register_new = findViewById(R.id.register_new);

        //Login

        Login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View view ) {
                String user = Username1.getText().toString();
                String pass = Password1.getText().toString();

                if (TextUtils.isEmpty(user) )
                    Username1.setError("Username Required");

                if (TextUtils.isEmpty(pass))
                     Password1.setError("Password Required");

                 else  {
                    Boolean checkuserpass = MyDatabase.checkusernamepassword(user,pass);
                    if (checkuserpass==true){
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Boolean checkusername = MyDatabase.checkusername(user);

                        if (checkusername==false){
                            Username1.setError("Check Username");
                        }
                        if (checkusername== true){
                            Password1.setError("Check Password");
                        }


                        //Username1.setError("Check Username");
                        //Password1.setError("Check Password");
                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //Guest_login
        guest_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent guest = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(guest);
            }
        });

        register_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View view ) {
                Intent user= new Intent(MainActivity.this,New_User_Register.class);
                startActivity(user);
            }
        });

    }
}