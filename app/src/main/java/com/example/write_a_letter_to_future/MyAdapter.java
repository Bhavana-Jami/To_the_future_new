package com.example.write_a_letter_to_future;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<Myholder> {
    Context c;
    ArrayList<Model> models;//this is created for define model in our class

    public MyAdapter(Context c, ArrayList<Model> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_of_option,null);
        return new Myholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Myholder Myholder, int position) {
        Myholder.mmain.setText(models.get(position).getMain_line());
        Myholder.msub.setText(models.get(position).getSub_line());
        Myholder.mImage.setImageResource(models.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
