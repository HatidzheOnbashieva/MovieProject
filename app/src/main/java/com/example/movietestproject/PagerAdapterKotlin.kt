package com.example.movietestproject

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class PagerAdapterKotlin(fragmentManager: FragmentManager?, private val numOfTabs: Int) : FragmentPagerAdapter(fragmentManager!!) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return PopularFragment()
            1 -> return TopRatedFragment()
            2 -> return FavouritesFragment()
            else -> return Fragment()
        }
    }

    override fun getCount(): Int {
        return numOfTabs
    }
}