package com.manohito.android.monochikadatabasecontroller.activity

import android.annotation.SuppressLint
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.manohito.android.monochikadatabasecontroller.R
import com.manohito.android.monochikadatabasecontroller.adapter.TopViewPagerAdapter
import kotterknife.bindView
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity

@SuppressLint("Registered")
@EActivity(R.layout.activity_main)
open class MainActivity : AppCompatActivity() {
    private val mTopViewPager: ViewPager by bindView(R.id.top_view_pager)
    private val mTopTab: TabLayout by bindView(R.id.top_tab)

    @AfterViews
    fun initTopViewPager() {
        mTopViewPager.adapter = TopViewPagerAdapter(supportFragmentManager)
        mTopTab.setupWithViewPager(mTopViewPager)
    }
}
