package com.example.movietestproject.Model

import com.google.gson.annotations.SerializedName

class MovieResultsKotlin( @SerializedName("results") var _results: List<MovieKotlin>? ) {

//    val results
//            get() = _results ?: emptyList()
//
//    init{
//        this.results
//    }
}