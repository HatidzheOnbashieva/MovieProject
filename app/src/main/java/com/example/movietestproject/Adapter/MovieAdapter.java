package com.example.movietestproject.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movietestproject.Model.Movie;
import com.example.movietestproject.MoviesInfoFragment;
import com.example.movietestproject.ViewHolder.OnItemClickListener;
import com.example.movietestproject.ViewHolder.MovieViewHolder;
import com.example.movietestproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<Movie> movies;
    private String imageURL, title, overview, originalTitle, releaseDate;
    private double rating;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, null);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movies.get(position).getPosterPath()).into(holder.poster);
        holder.title.setText(movies.get(position).getTitle());

        Context ctx = holder.itemView.getContext();

        holder.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                imageURL = movies.get(position).getPosterPath();
                title = movies.get(position).getTitle();
                overview = movies.get(position).getOverview();
                originalTitle = movies.get(position).getOriginalTitle();
                rating = movies.get(position).getVoteAverage();
                releaseDate = movies.get(position).getReleaseDate();

                MoviesInfoFragment fragment = new MoviesInfoFragment();
                Bundle bundle = new Bundle();
                bundle.putString("imageURL", imageURL);
                bundle.putString("title", title);
                bundle.putString("overview", overview);
                bundle.putString("originalTitle", originalTitle);
                bundle.putDouble("rating", rating);
                bundle.putString("releaseDate", releaseDate);
                fragment.setArguments(bundle);

                FragmentTransaction transaction = ((AppCompatActivity)ctx).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.mainScreen, fragment).addToBackStack("moviesfragment").commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
