package com.example.cardio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    List<String> name_id2;

    List<String> age_id2;

    List<String> mobiel_id2;

    LayoutInflater inflater;



    public MyAdapter ( Context context, ArrayList<String> name_id, ArrayList<String> age_id, ArrayList<String> mobiel_id ) {
        this.context = context;
        this.name_id2 = name_id;
        this.age_id2  =  age_id;
        this.mobiel_id2 =  mobiel_id;

        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder ( @NonNull ViewGroup parent, int viewType ) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_info,parent,false);
        return new MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder ( @NonNull MyAdapter.MyViewHolder holder, int position ) {
            MyViewHolder viewHolder = (MyViewHolder) holder;
            viewHolder.textname1.setText(name_id2.get(position));
            viewHolder.textage1.setText(age_id2.get(position));
            viewHolder.textmobiel1.setText(mobiel_id2.get(position));



    }


    @Override
    public int getItemCount () {
        return name_id2.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textname1;

        TextView textage1;

        TextView textmobiel1;
        public MyViewHolder ( @NonNull View itemView ) {
            super(itemView);
            textname1 = itemView.findViewById(R.id.textname);
            textage1 = itemView.findViewById(R.id.textage);
            textmobiel1=itemView.findViewById(R.id.textmobiel);


        }
    }
}
