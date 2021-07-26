package com.example.movietestproject.OnlineDB

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstanceKotlin {
    var BASE_URL = "https://api.themoviedb.org/"

    private var retrofitInstance: Retrofit? = null

    private fun RetrofitInstanceKotlin() {}

    fun getRetrofitInstanceKotlin(): Retrofit? {
        if (retrofitInstance == null) {
            retrofitInstance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofitInstance
    }
}