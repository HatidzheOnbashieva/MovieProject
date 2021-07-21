package com.example.movietestproject.ViewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movietestproject.DB.DataBaseConnectivity;
import com.example.movietestproject.Model.Movie;

import java.util.List;

public class MoviesInfoFragmentViewModel extends ViewModel {

    public DataBaseConnectivity dbConnection;

    private MutableLiveData<List<Movie>> moviesList;

    public MoviesInfoFragmentViewModel(Context context)
    {
        moviesList = new MutableLiveData<>();
        dbConnection = DataBaseConnectivity.getDBInstance(context);
    }



    public void addDataLocalDB(Movie movie, Context context)
    {
        boolean result = dbConnection.addData(movie.getTitle(), movie.getOverview(), movie.getOriginalTitle(), movie.getVoteAverage(), movie.getReleaseDate(), movie.getPosterPath());
        if(result == false)
        {
            Toast.makeText(context,"Unsuccessful data insert in the database!", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(context, "You successfully added this movie to your favourites list!", Toast.LENGTH_SHORT).show();
        }
    }
}
