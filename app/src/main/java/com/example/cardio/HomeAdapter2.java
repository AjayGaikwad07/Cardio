package com.example.cardio;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.List;

public class HomeAdapter2 extends RecyclerView.Adapter<HomeAdapter2.homeViewHolder> {

    List<String> titles2;
    List<String> info2;

    List<String> Contact2;


    LayoutInflater inflater;

    public HomeAdapter2(Context cxt, List<String> titles, List<String> info,List<String> Contact){
        this.titles2 = titles;
        this.info2 = info;
        this.Contact2=Contact;


        this.inflater = LayoutInflater.from(cxt);
//        this.cxt = cxt;
    }

    @NonNull
    @Override
    public homeViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.info_card_layout2,parent,false);
        return new homeViewHolder(view);
    }

    @Override
    public void onBindViewHolder( @NonNull homeViewHolder holder, int position) {
        homeViewHolder viewHolder = (homeViewHolder) holder;
        viewHolder.titleView.setText(titles2.get(position));
        viewHolder.infoView.setText(info2.get(position));
        viewHolder.ContactView.setText(Contact2.get(position));


    }

    @Override
    public int getItemCount() {
        return titles2.size();
    }

    public class homeViewHolder extends RecyclerView.ViewHolder {

        TextView ContactView;
        TextView titleView;
        TextView infoView;

        public homeViewHolder(@NonNull View itemView) {
            super(itemView);

            titleView = itemView.findViewById(R.id.infoItem2);
            infoView = itemView.findViewById(R.id.detailItem2);
            ContactView=itemView.findViewById(R.id.detailContact2);
        }
    }}
