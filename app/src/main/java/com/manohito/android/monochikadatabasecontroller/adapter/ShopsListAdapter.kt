package com.manohito.android.monochikadatabasecontroller.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.BaseAdapter
import com.manohito.android.monochikadatabasecontroller.R
import com.manohito.android.monochikadatabasecontroller.model.Shop
import com.manohito.android.monochikadatabasecontroller.view.ShopsListView

class ShopsListAdapter(private val context: Context) : BaseAdapter() {
    private var mShops: List<Shop> = emptyList()
    private var mLastAnimationPosition: Int = 0

    override fun getView(position: Int, currentView: View?, parent: ViewGroup?): View {
        val view = currentView ?: ShopsListView(context).apply {
            setShopsListView(mShops[position])
        }
        if (mLastAnimationPosition < position) {
            val animation = AnimationUtils.loadAnimation(context, R.anim.motion)
            currentView?.startAnimation(animation)
            mLastAnimationPosition = position
        }
        return view
    }

    override fun getItem(position: Int): Any = mShops[position]

    override fun getItemId(id: Int): Long = 0

    override fun getCount(): Int = mShops.size

    fun setShops(shops: List<Shop>) {
        this.mShops = shops
    }
}