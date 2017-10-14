package com.manohito.android.monochikadatabasecontroller.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View.*
import android.view.ViewGroup
import com.manohito.android.monochikadatabasecontroller.ApiService
import com.manohito.android.monochikadatabasecontroller.MonoChikaRetrofit
import com.manohito.android.monochikadatabasecontroller.R
import com.manohito.android.monochikadatabasecontroller.model.Shop
import com.manohito.android.monochikadatabasecontroller.view.holder.ShopViewHolder

open class ShopRecyclerAdapter : RecyclerView.Adapter<ShopViewHolder>() {
    private var mShops: MutableList<Shop> = mutableListOf()

    override fun getItemCount(): Int = mShops.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ShopViewHolder {
        return ShopViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.view_shop, parent, false))
    }

    override fun onBindViewHolder(holder: ShopViewHolder?, position: Int) {
        if (holder == null) return

        val shop = mShops[position]
        holder.mIdTextView.text = shop.id.toString()
        holder.mNameTextView.text = shop.name
        holder.mLatitudeTextView.text = shop.latitude.toString()
        holder.mLongitudeTextView.text = shop.longitude.toString()
        holder.mAddressTextView.text = shop.address
        holder.view.setOnClickListener { onItemClicked(holder) }
        holder.mDeleteButton.setOnClickListener { onDeleteButtonClicked(shop, position) }
    }

    fun setShops(shops: List<Shop>) {
        mShops = shops.toMutableList()
    }

    fun removeAtPosition(position: Int) {
        if (position < itemCount) {
            mShops.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    open fun onItemClicked(holder: ShopViewHolder) {}

    open fun onDeleteButtonClicked(shop: Shop, position: Int) {}
}