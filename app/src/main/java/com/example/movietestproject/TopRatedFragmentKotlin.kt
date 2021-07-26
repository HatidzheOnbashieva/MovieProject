package com.example.movietestproject

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TopRatedFragmentKotlin : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_top_rated, container, false)
        var recyclerView: RecyclerView = v.findViewById(R.id.movieRecyclerViewTopRated)
        var context: Context? = requireActivity().applicationContext
        var moviesFragmentViewModel: MoviesFragmentViewModel? = MoviesFragmentViewModel(context)
        //moviesFragmentViewModel = ViewModelProviders.of(this).get(MoviesFragmentViewModel.class);
        //moviesFragmentViewModel = ViewModelProviders.of(this).get(MoviesFragmentViewModel.class);
        recyclerView.setLayoutManager(GridLayoutManager(activity, 2))

        moviesFragmentViewModel.makeAPICallTopRated()
        moviesFragmentViewModel.getMovies().observe(activity,
            Observer<List<Any?>?> { movies ->
                var movieAdapter: MovieAdapter? = MovieAdapter(movies)
                recyclerView.setAdapter(movieAdapter)
            })

        return v


    }

    }