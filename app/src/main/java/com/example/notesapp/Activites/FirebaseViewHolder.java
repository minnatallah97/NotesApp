package com.example.notesapp.Activites;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.R;

public class FirebaseViewHolder extends RecyclerView.ViewHolder {
    public TextView title,Desc,Category;
    public FirebaseViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title_tv1);
        Desc = itemView.findViewById(R.id.desc_tv2);
        Category = itemView.findViewById(R.id.catrgory);
    }
}