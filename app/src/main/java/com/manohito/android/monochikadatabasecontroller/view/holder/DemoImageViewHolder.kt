package com.manohito.android.monochikadatabasecontroller.view.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.manohito.android.monochikadatabasecontroller.R

class DemoImageViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val mImage: ImageView = view.findViewById(R.id.demoImage)
}