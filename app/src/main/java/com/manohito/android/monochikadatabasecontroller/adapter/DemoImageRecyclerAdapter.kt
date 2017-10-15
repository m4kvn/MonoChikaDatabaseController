package com.manohito.android.monochikadatabasecontroller.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.manohito.android.monochikadatabasecontroller.R
import com.manohito.android.monochikadatabasecontroller.model.DemoImage
import com.manohito.android.monochikadatabasecontroller.view.holder.DemoImageViewHolder

open class DemoImageRecyclerAdapter(val context: Context) : RecyclerView.Adapter<DemoImageViewHolder>() {

    private var mDemoImages: MutableList<DemoImage> = mutableListOf(
            DemoImage(), DemoImage(), DemoImage(), DemoImage(), DemoImage(), DemoImage(), DemoImage()
    )

    override fun getItemCount(): Int = mDemoImages.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DemoImageViewHolder {
        return DemoImageViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.view_demo_image, parent, false))
    }

    override fun onBindViewHolder(holder: DemoImageViewHolder?, position: Int) {
        if (holder == null) return
        updateImageView(holder, mDemoImages[position])
    }

    open fun updateImageView(holder: DemoImageViewHolder, demoImage: DemoImage) {}
}