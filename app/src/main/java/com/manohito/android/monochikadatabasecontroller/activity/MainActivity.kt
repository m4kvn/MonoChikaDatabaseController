package com.manohito.android.monochikadatabasecontroller.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.manohito.android.monochikadatabasecontroller.R
import com.manohito.android.monochikadatabasecontroller.adapter.TopViewPagerAdapter
import kotterknife.bindView

class MainActivity : AppCompatActivity() {
    private val mTopViewPager: ViewPager by bindView(R.id.top_view_pager)
    private val mTopTab: TabLayout by bindView(R.id.top_tab)

    companion object {
        const val RESULT_SHOPS = 1000
        const val RESULT_MAKERS = 1001
        const val RESULT_MAIN_CATEGORY = 1002
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTopViewPager.adapter = TopViewPagerAdapter(supportFragmentManager)
        mTopTab.setupWithViewPager(mTopViewPager)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("MainActivity", "requestCode: $resultCode, resultCode: $resultCode, data: $data")

        mTopViewPager.setCurrentItem(when (resultCode) {
            RESULT_SHOPS -> 0
            RESULT_MAKERS -> 1
            RESULT_MAIN_CATEGORY -> 2
            else -> 0
        }, false)
    }
}
