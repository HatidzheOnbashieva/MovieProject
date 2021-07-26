package com.example.movietestproject.ViewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movietestproject.R

class MovieViewHolderKotlin(view:View): RecyclerView.ViewHolder(view), View.OnClickListener  {

    var poster: ImageView? = null
    var title: TextView? = null
    var itemClickListenerKotlin: OnItemClickListenerKotlin? = null

    fun MovieViewHolder(itemView: View) {
        //super(itemView)
        poster = itemView.findViewById(R.id.moviePoster)
        title = itemView.findViewById(R.id.title)
        itemView.setOnClickListener(this)
    }
    override fun onClick(view: View?) {
        itemClickListenerKotlin!!.onItemClickListener(view, layoutPosition)
    }

    fun setItemClickListener(itemClick: OnItemClickListenerKotlin?) {
        itemClickListenerKotlin = itemClick
    }
}