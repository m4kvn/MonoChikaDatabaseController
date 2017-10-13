package com.manohito.android.monochikadatabasecontroller.view.holder

import android.support.constraint.Group
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.manohito.android.monochikadatabasecontroller.R

class MakerViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val mIdTextView: TextView = view.findViewById(R.id.maker_detail_id)
    val mNameTextView: TextView = view.findViewById(R.id.maker_detail_name)
    val mDeleteButton: Button = view.findViewById(R.id.maker_detail_delete)
    val mUpdateButton: Button = view.findViewById(R.id.maker_detail_update)
    val mExpandGroup: Group = view.findViewById(R.id.maker_detail_group)
}