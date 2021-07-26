package com.example.movietestproject.Adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.movietestproject.Model.Movie
import com.example.movietestproject.MoviesInfoFragment
import com.example.movietestproject.R
import com.example.movietestproject.ViewHolder.MovieViewHolder
import com.squareup.picasso.Picasso

class MovieAdapterKotlin(movies: List<Movie>?): RecyclerView.Adapter<MovieViewHolder>() {

    private var movies: List<Movie>? = null
    var imageURL: String? = null
    var title: String? = null
    var overview: String? = null
    var originalTitle: String? = null
    var releaseDate: String? = null
    var rating: Double = 0.0

    init{
        this.movies = movies
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, null)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movies!![position].posterPath)
            .into(holder.poster)
        holder.title.text = movies!![position].title

        val ctx = holder.itemView.context

        holder.setItemClickListener { view, position ->
            imageURL = movies!![position].posterPath
            title = movies!![position].title
            overview = movies!![position].overview
            originalTitle = movies!![position].originalTitle
            rating = movies!![position].voteAverage
            releaseDate = movies!![position].releaseDate
            val fragment = MoviesInfoFragment()
            val bundle = Bundle()
            bundle.putString("imageURL", imageURL)
            bundle.putString("title", title)
            bundle.putString("overview", overview)
            bundle.putString("originalTitle", originalTitle)
            bundle.putDouble("rating", rating)
            bundle.putString("releaseDate", releaseDate)
            fragment.arguments = bundle
            val transaction = (ctx as AppCompatActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.mainScreen, fragment).addToBackStack("moviesfragment").commit()
        }
    }

    override fun getItemCount(): Int {
        return movies!!.size
    }
}