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
import com.example.movietestproject.Model.MovieKotlin
import com.example.movietestproject.ViewModel.MoviesFragmentViewModelKotlin

class FavouritesFragmentKotlin : Fragment() {

    //lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_favourites, container, false)
        val recyclerView: RecyclerView = v.findViewById(R.id.movieRecyclerViewFavourites)

        var context: Context? = requireActivity().applicationContext
        var moviesFragmentViewModel: MoviesFragmentViewModelKotlin? = MoviesFragmentViewModelKotlin(context)
        //moviesFragmentViewModel = ViewModelProviders.of(this).get(MoviesFragmentViewModel.class);
        //moviesFragmentViewModel = ViewModelProviders.of(this).get(MoviesFragmentViewModel.class);
        recyclerView.setLayoutManager(GridLayoutManager(activity, 2))

        moviesFragmentViewModel?.makeLocalDBCall()
        moviesFragmentViewModel?.getMovies()?.observe(requireActivity(),
            Observer<List<MovieKotlin>> { movies ->
                var movieAdapter = MovieAdapterKotlin(movies)
                recyclerView.setAdapter(movieAdapter)
            })

        return v

    }
}