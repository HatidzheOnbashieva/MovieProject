package com.example.movietestproject

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso


class MovieInfoFragmentKotlin : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_movies_info, container, false)


        var backButton: ImageView = v.findViewById(R.id.back)
        var poster: ImageView = v.findViewById<ImageView>(R.id.moviePoster)
        var favourite: ImageView = v.findViewById<ImageView>(R.id.favourite)
        var title: TextView = v.findViewById(R.id.movieTitle)
        var overview: TextView = v.findViewById<TextView>(R.id.movieOverview)
        var originalTitle: TextView = v.findViewById<TextView>(R.id.movieOriginalTitle)
        var releaseDate: TextView = v.findViewById<TextView>(R.id.movieReleaseDate)
        var rating: TextView =  v.findViewById<TextView>(R.id.movieRating)
        var context: Context? = requireActivity().applicationContext
        var moviesInfoFragmentViewModel: MoviesInfoFragmentViewModel? = MoviesInfoFragmentViewModel(context)

        var imageUrl: String? = this.requireArguments().getString("imageURL")
        var movieTitle:String? = this.requireArguments().getString("title")
        var movieOverview:String? = this.requireArguments().getString("overview")
        var movieOriginalTitle:String? = this.requireArguments().getString("originalTitle")
        var movieRating: Double? = this.requireArguments().getDouble("rating", 2.0)
        var movieReleaseDate:String? = this.requireArguments().getString("releaseDate")

        Picasso.get().load("https://image.tmdb.org/t/p/w500$imageUrl").into(poster)
        title.setText(movieTitle)
        overview.setText(movieOverview)
        originalTitle.setText(movieOriginalTitle)
        val convertedToStringRating = movieRating.toString()
        rating.setText(convertedToStringRating)
        releaseDate.setText(movieReleaseDate)

        backButton.setOnClickListener(View.OnClickListener { //MoviesFragment moviesFragment = new MoviesFragment();
            //FragmentTransaction transaction = getFragmentManager().beginTransaction();
            //transaction.replace(R.id.mainScreen, moviesFragment).commit();
            if (fragmentManager != null) {
                requireFragmentManager().popBackStack()
            }
        })

        favourite.setOnClickListener(View.OnClickListener {
            val movie = Movie(
                movieTitle,
                movieOverview,
                movieOriginalTitle,
                movieRating,
                movieReleaseDate,
                imageUrl
            )
            moviesInfoFragmentViewModel.addDataLocalDB(movie, context)
        })

        return v
    }
}