package com.example.movietestproject;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.movietestproject.Adapter.MovieAdapter;
import com.example.movietestproject.Model.Movie;
import com.example.movietestproject.ViewModel.MoviesFragmentViewModel;


import java.util.List;

public class MoviesFragment extends Fragment {

    Button popular, topRated, favourites;
    RecyclerView recyclerView;
    MovieAdapter movieAdapter;

    private MoviesFragmentViewModel moviesFragmentViewModel;
    public Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movies, container, false);
        popular = v.findViewById(R.id.popular);
        topRated = v.findViewById(R.id.topRated);
        favourites = v.findViewById(R.id.favourites);
        recyclerView = v.findViewById(R.id.movieRecyclerView);

        context = getActivity().getApplicationContext();
        moviesFragmentViewModel = new MoviesFragmentViewModel(context);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));


        popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moviesFragmentViewModel.makeAPICallPopular();
                moviesFragmentViewModel.getMovies().observe(getActivity(), new Observer<List<Movie>>() {
                    @Override
                    public void onChanged(List<Movie> movies) {
                        movieAdapter = new MovieAdapter(movies);
                        recyclerView.setAdapter(movieAdapter);
                    }
                });

                    }
        });

        topRated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moviesFragmentViewModel.makeAPICallTopRated();
                moviesFragmentViewModel.getMovies().observe(getActivity(), new Observer<List<Movie>>() {
                    @Override
                    public void onChanged(List<Movie> movies) {
                        movieAdapter = new MovieAdapter(movies);
                        recyclerView.setAdapter(movieAdapter);
                    }
                });
                    }
        });

        favourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moviesFragmentViewModel.makeLocalDBCall();
                moviesFragmentViewModel.getMovies().observe(getActivity(), new Observer<List<Movie>>() {
                    @Override
                    public void onChanged(List<Movie> movies) {
                        movieAdapter = new MovieAdapter(movies);
                        recyclerView.setAdapter(movieAdapter);
                    }
                });

            }
        });

        return v;
    }
}