package com.manohito.android.monochikadatabasecontroller.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View.*
import android.view.ViewGroup
import com.manohito.android.monochikadatabasecontroller.R
import com.manohito.android.monochikadatabasecontroller.model.Shop
import com.manohito.android.monochikadatabasecontroller.view.holder.ShopViewHolder

class ShopRecyclerAdapter : RecyclerView.Adapter<ShopViewHolder>() {
    private var mShops: List<Shop> = emptyList()

    override fun getItemCount(): Int = mShops.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ShopViewHolder {
        return ShopViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.view_shop, parent, false))
    }

    override fun onBindViewHolder(holder: ShopViewHolder?, position: Int) {
        holder?.let{
            val shop = mShops[position]
            it.mIdTextView.text = shop.id.toString()
            it.mNameTextView.text = shop.name
            it.mLatitudeTextView.text = shop.latitude.toString()
            it.mLongitudeTextView.text = shop.longitude.toString()
            it.mAddressTextView.text = shop.address
            it.view.setOnClickListener { _ ->
                it.mExpandGroup.visibility = when (it.mExpandGroup.visibility) {
                    VISIBLE -> GONE
                    else -> VISIBLE
                }
            }
        }
    }

    fun setShops(shops: List<Shop>) {
        mShops = shops
    }
}