package com.example.movietestproject.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movietestproject.R;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ImageView poster;
    public TextView title;
    OnItemClickListener itemClickListener;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        this.poster = itemView.findViewById(R.id.moviePoster);
        this.title = itemView.findViewById(R.id.title);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClickListener(view, getLayoutPosition());
    }

    public void setItemClickListener(OnItemClickListener itemClick)
    {
        this.itemClickListener = itemClick;
    }

}