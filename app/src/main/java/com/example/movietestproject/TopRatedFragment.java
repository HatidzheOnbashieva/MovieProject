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

import com.example.movietestproject.Adapter.MovieAdapter;
import com.example.movietestproject.Model.Movie;
import com.example.movietestproject.ViewModel.MoviesFragmentViewModel;

import java.util.List;

public class TopRatedFragment extends Fragment {

    RecyclerView recyclerView;
    MovieAdapter movieAdapter;

    private MoviesFragmentViewModel moviesFragmentViewModel;
    public Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_top_rated, container, false);
        recyclerView = v.findViewById(R.id.movieRecyclerViewTopRated);
        context = getActivity().getApplicationContext();
        moviesFragmentViewModel = new MoviesFragmentViewModel(context);
        //moviesFragmentViewModel = ViewModelProviders.of(this).get(MoviesFragmentViewModel.class);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));



        moviesFragmentViewModel.makeAPICallTopRated();
        moviesFragmentViewModel.getMovies().observe(getActivity(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                movieAdapter = new MovieAdapter(movies);
                recyclerView.setAdapter(movieAdapter);
            }
        });

        return v;
    }

}