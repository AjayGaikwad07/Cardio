package com.example.cardio;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Userlist extends AppCompatActivity {

    RecyclerView preView;
    ArrayList<String> name_id2,age_id2,mobiel_id2;
    database db;
    MyAdapter adapter;

    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);

        // hide Action Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_info);
        db = new database(this);
        name_id2 =new ArrayList<>();
        age_id2 = new ArrayList<>();
        mobiel_id2 = new ArrayList<>();
        preView = findViewById(R.id.preView);

        adapter =new MyAdapter(this, name_id2,age_id2,mobiel_id2);
        preView.setAdapter(adapter);
        preView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();



    }

    private void displaydata() {
        Cursor cursor = db.getdata();
        {

            Toast.makeText(this, "No Entry Exists", Toast.LENGTH_SHORT).show();

        }
    }
}
