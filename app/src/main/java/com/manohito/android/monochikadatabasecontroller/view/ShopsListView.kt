package com.manohito.android.monochikadatabasecontroller.view

import android.content.Context
import android.support.constraint.Group
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import com.manohito.android.monochikadatabasecontroller.R
import com.manohito.android.monochikadatabasecontroller.model.Shop

class ShopsListView : CardView {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_shop, this)
        setOnClickListener {
            findViewById<Group>(R.id.shop_detail_group).apply {
                visibility = if (visibility == GONE) {
                    VISIBLE
                } else {
                    GONE
                }
            }
        }
    }

    fun setShopsListView(shop: Shop) {
        findViewById<TextView>(R.id.shop_detail_id).apply {
            text = shop.id.toString()
        }
        findViewById<TextView>(R.id.shop_detail_name).apply {
            text = shop.name
        }
        findViewById<TextView>(R.id.shop_detail_latitude).apply {
            text = shop.latitude.toString()
        }
        findViewById<TextView>(R.id.shop_detail_longitude).apply {
            text = shop.longitude.toString()
        }
        findViewById<TextView>(R.id.shop_detail_address).apply {
            text = shop.address
        }
    }
}