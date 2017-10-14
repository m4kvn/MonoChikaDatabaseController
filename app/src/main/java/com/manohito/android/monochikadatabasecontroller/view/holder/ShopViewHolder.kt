package com.manohito.android.monochikadatabasecontroller.view.holder

import android.support.constraint.Group
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.manohito.android.monochikadatabasecontroller.R

class ShopViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val mIdTextView: TextView = view.findViewById(R.id.shop_detail_id)
    val mNameTextView: TextView = view.findViewById(R.id.shop_detail_name)
    val mLatitudeTextView: TextView = view.findViewById(R.id.shop_detail_latitude)
    val mLongitudeTextView: TextView = view.findViewById(R.id.shop_detail_longitude)
    val mAddressTextView: TextView = view.findViewById(R.id.shop_detail_address)
    val mDeleteButton: Button = view.findViewById(R.id.shop_detail_delete)
    val mUpdateButton: Button = view.findViewById(R.id.shop_detail_update)
    val mExpandGroup: Group = view.findViewById(R.id.shop_detail_group)
}