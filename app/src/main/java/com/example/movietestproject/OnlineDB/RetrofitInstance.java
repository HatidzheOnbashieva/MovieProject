package com.example.movietestproject.OnlineDB;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    public static String BASE_URL = "https://api.themoviedb.org/";

    private static Retrofit retrofitInstance;

    private RetrofitInstance(){}

    public static Retrofit getRetrofitInstance()
    {
        if(retrofitInstance==null)
        {
            retrofitInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitInstance;
    }


}
