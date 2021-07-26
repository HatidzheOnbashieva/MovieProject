package com.example.movietestproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity2Kotlin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activity2_kotlin)

        val tabLayout: TabLayout = findViewById(R.id.tabBar)
        val viewPager: ViewPager = findViewById(R.id.viewPager)

        val pagerAdapter = PagerAdapterKotlin(
            supportFragmentManager, tabLayout.tabCount
        )
        viewPager.setAdapter(pagerAdapter)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }
}