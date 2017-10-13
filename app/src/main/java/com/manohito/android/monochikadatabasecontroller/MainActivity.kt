package com.manohito.android.monochikadatabasecontroller

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.manohito.android.monochikadatabasecontroller.adapter.TopViewPagerAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val topViewPager = findViewById<ViewPager>(R.id.top_view_pager).apply {
            adapter = TopViewPagerAdapter(supportFragmentManager)
        }

        findViewById<TabLayout>(R.id.top_tab).apply {
            setupWithViewPager(topViewPager)
        }
    }
}
