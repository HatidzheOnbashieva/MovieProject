package com.example.movietestproject.ViewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movietestproject.DB.DataBaseConnectivity
import com.example.movietestproject.Model.Movie
import com.example.movietestproject.Model.MovieKotlin
import com.example.movietestproject.Model.MovieResults
import com.example.movietestproject.OnlineDB.API
import com.example.movietestproject.OnlineDB.RetrofitInstanceKotlin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MoviesFragmentViewModelKotlin: ViewModel() {
    var API_KEY:String = "d427f7ee4cda19464d0ffca2218445be"
    var LANGUAGE = "en-US"
    var PAGE = 1
    var CATEGORY1 = "popular"
    var CATEGORY2 = "top_rated"
    var movieApi: API? = null
    var dbConnection: DataBaseConnectivity? = null

    var moviesList: MutableLiveData<List<MovieKotlin>>? = null

    fun MoviesFragmentViewModelKotlin(context: Context?) {
        moviesList = MutableLiveData()
        MoviesFragmentViewModelKotlin.movieApi = RetrofitInstanceKotlin.getRetrofitInstanceKotlin().create(APIKotlin::class.java)
        dbConnection = DataBaseConnectivity.getDBInstance(context)
    }

    fun getMovies(): MutableLiveData<List<MovieKotlin>>? {
        return moviesList
    }

    fun makeAPICallPopular() {
        val call1 = MoviesFragmentViewModelKotlin.movieApi.getMovies(
            MoviesFragmentViewModel.CATEGORY1,
            MoviesFragmentViewModel.API_KEY,
            MoviesFragmentViewModel.LANGUAGE,
            MoviesFragmentViewModel.PAGE
        )
        call1.enqueue(object : Callback<MovieResults?> {
            override fun onResponse(call: Call<MovieResults?>, response: Response<MovieResults?>) {
                val movieResults = response.body()
                val movies = movieResults!!.results
                moviesList!!.postValue(movies)
            }

            override fun onFailure(call: Call<MovieResults?>, t: Throwable) {
                t.message
            }
        })
    }

    fun makeAPICallTopRated() {
        val call2 = MoviesFragmentViewModel.movieApi.getMovies(
            MoviesFragmentViewModel.CATEGORY2,
            MoviesFragmentViewModel.API_KEY,
            MoviesFragmentViewModel.LANGUAGE,
            MoviesFragmentViewModel.PAGE
        )
        call2.enqueue(object : Callback<MovieResults?> {
            override fun onResponse(call: Call<MovieResults?>, response: Response<MovieResults?>) {
                val movieResults = response.body()
                val movies = movieResults!!.results
                moviesList!!.postValue(movies)
            }

            override fun onFailure(call: Call<MovieResults?>, t: Throwable) {
                t.message
            }
        })
    }

    fun makeLocalDBCall()
    {
        val data = dbConnection!!.contents
        val movies: MutableList<Movie> = ArrayList()

        if (data.count == 0) {
            //Toast.makeText(getActivity(), "The database is empty!", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                movies.add(
                    Movie(
                        data.getString(1),
                        data.getString(2),
                        data.getString(3),
                        data.getDouble(4),
                        data.getString(5),
                        data.getString(6)
                    )
                )
                moviesList!!.postValue(movies)
            }
        }
    }

}