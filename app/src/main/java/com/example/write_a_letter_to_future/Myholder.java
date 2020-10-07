package com.example.write_a_letter_to_future;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Myholder extends RecyclerView.ViewHolder {
    ImageView mImage;
    TextView mmain,msub;
    public Myholder(@NonNull View itemView) {
        super(itemView);
        this.mImage=itemView.findViewById(R.id.img);
        this.mmain=itemView.findViewById(R.id.main_line);
        this.msub=itemView.findViewById(R.id.sub_line);
    }
}
