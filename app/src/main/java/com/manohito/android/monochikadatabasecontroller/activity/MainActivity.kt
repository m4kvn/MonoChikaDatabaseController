package com.manohito.android.monochikadatabasecontroller.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.manohito.android.monochikadatabasecontroller.ActivityResult
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        mTopViewPager.setCurrentItem(when (resultCode) {
            ActivityResult.RESULT_SHOPS -> 0
            ActivityResult.RESULT_MAKERS -> 1
            ActivityResult.RESULT_MAIN_CATEGORY -> 2
            else -> 0
        }, false)
    }
}
