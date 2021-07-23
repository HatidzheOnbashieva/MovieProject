package com.example.movietestproject;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;
    public PagerAdapter(FragmentManager fragmentManager, int numOfTabs)
    {
        super(fragmentManager);
        this.numOfTabs = numOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0: return new PopularFragment();
            case 1: return new TopRatedFragment();
            case 2: return new FavouritesFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
