package com.example.movietestproject.ViewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movietestproject.DB.DataBaseConnectivity
import com.example.movietestproject.Model.Movie

class MoviesInfoFragmentViewModelKotlin: ViewModel() {
    var dbConnection: DataBaseConnectivity? = null

    private var moviesList: MutableLiveData<List<Movie>>? = null

    fun MoviesInfoFragmentViewModel(context: Context?) {
        moviesList = MutableLiveData()
        dbConnection = DataBaseConnectivity.getDBInstance(context)
    }


    fun addDataLocalDB(movie: Movie, context: Context?) {
        val result = dbConnection!!.addData(
            movie.title,
            movie.overview,
            movie.originalTitle,
            movie.voteAverage,
            movie.releaseDate,
            movie.posterPath
        )
        if (result == false) {
            Toast.makeText(context, "Unsuccessful data insert in the database!", Toast.LENGTH_LONG)
                .show()
        } else {
            Toast.makeText(
                context,
                "You successfully added this movie to your favourites list!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}