package com.manohito.android.monochikadatabasecontroller.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.manohito.android.monochikadatabasecontroller.R
import com.manohito.android.monochikadatabasecontroller.model.Maker
import com.manohito.android.monochikadatabasecontroller.view.holder.MakerViewHolder

open class MakerRecyclerAdapter : RecyclerView.Adapter<MakerViewHolder>() {
    private var mMakers: MutableList<Maker> = mutableListOf()

    override fun getItemCount(): Int = mMakers.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MakerViewHolder {
        return MakerViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.view_maker, parent, false))
    }

    override fun onBindViewHolder(holder: MakerViewHolder?, position: Int) {
        if (holder == null) return

        val maker = mMakers[position]
        holder.mIdTextView.text = maker.id.toString()
        holder.mNameTextView.text = maker.name
        holder.view.setOnClickListener { onItemClicked(holder) }
        holder.mDeleteButton.setOnClickListener { onDeleteButtonClicked(maker, position) }
    }

    fun setMakers(makers: List<Maker>) {
        mMakers = makers.toMutableList()
    }

    fun removeAtPosition(position: Int) {
        if (position < itemCount) {
            mMakers.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    open fun onItemClicked(holder: MakerViewHolder) {}

    open fun onDeleteButtonClicked(maker: Maker, position: Int) {}
}