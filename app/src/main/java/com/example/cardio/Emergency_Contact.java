package com.example.cardio;

import static com.example.cardio.R.id.Call_button;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Emergency_Contact extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide Action Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_emergency_contact);

        Button Call_button,message_button,email_button;

        Call_button = findViewById(R.id.Call_button);
        message_button = findViewById(R.id.message_button);
        email_button = findViewById(R.id.email_button);



        Call_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iDial = new Intent(Intent.ACTION_DIAL);
                iDial.setData(Uri.parse("tel: +02266986666"));
                startActivity(iDial);
            }
        });


        message_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iMeg = new Intent(Intent.ACTION_SENDTO);
                iMeg.setData(Uri.parse("smsto:"+Uri.encode("+02266986666")));
                iMeg.putExtra("sms body","I want to help heart related");
                startActivity(iMeg);
            }
        });


        email_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iEmail = new Intent(Intent.ACTION_SEND);
                iEmail.setType("message/rfc822");
                iEmail.putExtra(Intent.EXTRA_EMAIL,new String[]{"info@ahirc.com"});
                iEmail.putExtra(Intent.EXTRA_SUBJECT,"Qureies");
                iEmail.putExtra(Intent.EXTRA_TEXT,"I want to help heart related");
                startActivity(Intent.createChooser(iEmail,"Email"));
            }
        });






     }
}