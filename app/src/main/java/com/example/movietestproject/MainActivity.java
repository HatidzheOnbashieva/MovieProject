package com.example.movietestproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SplashScreenFragment splashScreen = new SplashScreenFragment();
        //getSupportFragmentManager().beginTransaction().add(R.id.mainScreen, splashScreen).commit();

        TabLayout tabLayout = findViewById(R.id.tabBar);
//        TabItem tabPopular = findViewById(R.id.popular);
//        TabItem tabTopRated = findViewById(R.id.topRated);
//        TabItem tabFavourites = findViewById(R.id.favourites);
        ViewPager viewPager = findViewById(R.id.viewPager);

        PagerAdapterKotlin pagerAdapterKotlin = new PagerAdapterKotlin(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapterKotlin);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}