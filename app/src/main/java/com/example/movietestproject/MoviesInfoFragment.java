package com.example.movietestproject;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.movietestproject.Model.Movie;
import com.example.movietestproject.ViewModel.MoviesInfoFragmentViewModel;
import com.squareup.picasso.Picasso;


public class MoviesInfoFragment extends Fragment {

    private ImageView backButton, poster, favourite;
    private TextView title, overview, originalTitle, releaseDate, rating;
    private String imageUrl, movieTitle, movieOverview, movieOriginalTitle, movieReleaseDate;
    private Double movieRating;
    private MoviesInfoFragmentViewModel moviesInfoFragmentViewModel;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      View v = inflater.inflate(R.layout.fragment_movies_info, container, false);

        backButton = v.findViewById(R.id.back);
        poster = v.findViewById(R.id.moviePoster);
        favourite = v.findViewById(R.id.favourite);
        title = v.findViewById(R.id.movieTitle);
        overview = v.findViewById(R.id.movieOverview);
        originalTitle = v.findViewById(R.id.movieOriginalTitle);
        releaseDate =v.findViewById(R.id.movieReleaseDate);
        rating = v.findViewById(R.id.movieRating);
        context = getActivity().getApplicationContext();
        moviesInfoFragmentViewModel = new MoviesInfoFragmentViewModel(context);

        imageUrl = this.getArguments().getString("imageURL");
        movieTitle = this.getArguments().getString("title");
        movieOverview = this.getArguments().getString("overview");
        movieOriginalTitle = this.getArguments().getString("originalTitle");
        movieRating = this.getArguments().getDouble("rating", 2);
        movieReleaseDate = this.getArguments().getString("releaseDate");

        Picasso.get().load("https://image.tmdb.org/t/p/w500" + imageUrl).into(poster);
        title.setText(movieTitle);
        overview.setText(movieOverview);
        originalTitle.setText(movieOriginalTitle);
        String convertedToStringRating = movieRating.toString();
        rating.setText(convertedToStringRating);
        releaseDate.setText(movieReleaseDate);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoviesFragment moviesFragment = new MoviesFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.mainScreen, moviesFragment).commit();
            }
        });

        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Movie movie = new Movie(movieTitle, movieOverview, movieOriginalTitle,movieRating, movieReleaseDate,imageUrl);
              moviesInfoFragmentViewModel.addDataLocalDB(movie, context);
            }
        });

        return v;
    }
}