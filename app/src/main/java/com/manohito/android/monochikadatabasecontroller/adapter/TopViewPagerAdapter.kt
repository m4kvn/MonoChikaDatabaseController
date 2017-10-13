package com.manohito.android.monochikadatabasecontroller.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.manohito.android.monochikadatabasecontroller.fragment.MakersFragment
import com.manohito.android.monochikadatabasecontroller.fragment.ShopsFragment

class TopViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val pages: Map<Int, Pair<String, Class<out Fragment>>> = mapOf(
            0 to ("Shops" to ShopsFragment::class.java),
            1 to ("Makers" to MakersFragment::class.java)
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]?.second?.newInstance() ?: ShopsFragment()
    }

    override fun getCount(): Int = pages.size

    override fun getPageTitle(position: Int): CharSequence = pages[position]?.first ?: ""
}