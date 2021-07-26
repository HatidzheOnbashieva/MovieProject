package com.example.movietestproject.OnlineDB

import com.example.movietestproject.Model.MovieResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIKotlin {

    @GET("3/movie/{category}")
    fun getMovies(
        @Path("category") category: String?,
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("page") page: Int
    ): Call<MovieResults?>?
}