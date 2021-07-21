package com.example.movietestproject.ViewModel;

import android.content.Context;
import android.database.Cursor;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movietestproject.DB.DataBaseConnectivity;
import com.example.movietestproject.Model.Movie;
import com.example.movietestproject.Model.MovieResults;
import com.example.movietestproject.OnlineDB.API;
import com.example.movietestproject.OnlineDB.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesFragmentViewModel extends ViewModel {

    public static String API_KEY = "d427f7ee4cda19464d0ffca2218445be";
    public static String LANGUAGE = "en-US";
    public static int PAGE = 1;
    public static String CATEGORY1="popular";
    public static String CATEGORY2="top_rated";
    public static API movieApi;
    public DataBaseConnectivity dbConnection;

    private MutableLiveData<List<Movie>> moviesList;

    public MoviesFragmentViewModel(Context context)
    {
        moviesList = new MutableLiveData<>();
        movieApi  = RetrofitInstance.getRetrofitInstance().create(API.class);
        dbConnection = DataBaseConnectivity.getDBInstance(context);
    }

    public MutableLiveData<List<Movie>> getMovies()
    {
        return moviesList;
    }

    public void makeAPICallPopular()
    {
        Call<MovieResults> call1 = movieApi.getMovies(CATEGORY1, API_KEY, LANGUAGE, PAGE);
        call1.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                MovieResults movieResults = response.body();
                List<Movie> movies = movieResults.getResults();
                moviesList.postValue(movies);
            }
            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                t.getMessage();
            }
        });
    }

    public void makeAPICallTopRated()
    {
        Call<MovieResults> call2 = movieApi.getMovies(CATEGORY2, API_KEY, LANGUAGE, PAGE);
        call2.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                MovieResults movieResults = response.body();
                List<Movie> movies = movieResults.getResults();
                moviesList.postValue(movies);

            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                t.getMessage();
            }
        });
    }

    public void makeLocalDBCall() {
        Cursor data = dbConnection.getContents();
        List<Movie> movies = new ArrayList<Movie>();

        if (data.getCount() == 0) {
            //Toast.makeText(getActivity(), "The database is empty!", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                movies.add(new Movie(data.getString(1), data.getString(2), data.getString(3), data.getDouble(4), data.getString(5), data.getString(6)));
                moviesList.postValue(movies);
            }
        }
    }
}
