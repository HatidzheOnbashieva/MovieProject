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
import com.example.movietestproject.Adapter.MovieAdapterKotlin
import com.example.movietestproject.ViewModel.MoviesFragmentViewModelKotlin


class PopularFragmentKotlin : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_popular, container, false)
        var recyclerView: RecyclerView = v.findViewById(R.id.movieRecyclerViewPopular)
        var context: Context? = requireActivity().applicationContext
        var moviesFragmentViewModel = MoviesFragmentViewModelKotlin(context)
        //moviesFragmentViewModel = ViewModelProviders.of(this).get(MoviesFragmentViewModel.class);
        //moviesFragmentViewModel = ViewModelProviders.of(this).get(MoviesFragmentViewModel.class);
        recyclerView.setLayoutManager(GridLayoutManager(activity, 2))

        moviesFragmentViewModel?.makeAPICallPopular()
        moviesFragmentViewModel?.getMovies()?.observe(requireActivity(),
            Observer<List<MovieKotlin?>?> { movies ->
                var movieAdapter: MovieAdapterKotlin? = MovieAdapterKotlin(movies)
                recyclerView.setAdapter(movieAdapter)
            })

        return v

    }

    }