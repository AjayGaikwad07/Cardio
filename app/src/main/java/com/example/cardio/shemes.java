package com.example.cardio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class shemes extends AppCompatActivity {

    List<String> titles2;

    List<String> info2;

    List<String> Contact2;

    RecyclerView preview2;

    HomeAdapter2 adapter;


    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);

        // hide Action Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_shemes);

        titles2 = new ArrayList<>();
        info2 = new ArrayList<>();
        Contact2 = new ArrayList<>();

        preview2= findViewById(R.id.preview2);

        titles2.add("Mahatma Jyotiba Phule Jan Arogya Yojana");
        info2.add(" Universal health care scheme run by the Government of Maharashtra for the poor people of the state of Maharashtra who holds one of the 4 cards issued by the government; Antyodaya card, Annapurna card, yellow ration card or orange ration card. The scheme was first launched in 8 districts of the Maharashtra state in July 2012 and then across all 35 districts of the state in November 2015. It provides free access to medical care in government empanelled 488 hospitals for 971 types of diseases, surgeries and therapies costing up to Rs.1,50,000 per year per family (Rs.2,50,000 only for renal transplant). As of 17 January 2016, around 11.81 lakh procedures amounting to Rs.1827 crore have been performed on patients from 7.13 lakh beneficiary families which includes over 7.27 lakh surgeries and therapies. The scheme is called successful amid some allegations of hospitals directly or indirectly causing patients to incur out-of-pockets expenses on some part of the treatment.");
        Contact2.add("jeevandayee.gov.in");

        titles2.add("Ayushman Bharat Yojana Scheme");
        info2.add("Ayushman Bharat Yojana or National Health Protection Scheme is a program which aims to provide a service to create a healthy, capable and content new India. Presently it is running as a pilot project in Uttar Pradesh from 4 September 2018. It has two goals, one, creating a network of health and wellness infrastructure across the nation to deliver comprehensive primary healthcare services, and another is to provide insurance cover to at least 40 per cent of India's population which is majorly deprived of secondary and tertiary care services. This centrally sponsored flagship scheme aims to provide an annual health cover of up to Rs. 5 lakh to vulnerable 10 crore families (approximately 50 crore persons – 40% of country’s population) based on Socio Economic and Caste Census database.");
        Contact2.add("pm-nhpmission@gov.in");


        titles2.add("Rashtriya Arogya Nidhi (RAN)");
        info2.add("The Scheme provides for financial assistance to patients, living below poverty line and who is suffering from major life threatening diseases, to receive medical treatment at any of the super specialty Government hospitals / institutes.It is a scheme to provide financial assistance to poor patients living below poverty line and suffering from cancer, for their treatment at 27 Regional cancer centers (RCCs). Revolving Funds have been created in all the 27 Regional Cancer Centres (RCCs) and funds up to Rs. 50 lakhs will be placed at their disposal.");
        Contact2.add("so.grants-mhfw@nic.in");


        adapter = new HomeAdapter2(this,titles2,info2,Contact2);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        preview2.setLayoutManager(gridLayoutManager);
        preview2.setAdapter((RecyclerView.Adapter)adapter);
    }
}