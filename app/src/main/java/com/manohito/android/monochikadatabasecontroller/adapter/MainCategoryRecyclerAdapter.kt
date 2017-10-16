package com.manohito.android.monochikadatabasecontroller.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.manohito.android.monochikadatabasecontroller.R
import com.manohito.android.monochikadatabasecontroller.model.MainCategory
import com.manohito.android.monochikadatabasecontroller.view.holder.MainCategoryViewHolder

open class MainCategoryRecyclerAdapter : RecyclerView.Adapter<MainCategoryViewHolder>() {
    private var mMainCategories: MutableList<MainCategory> = mutableListOf()

    override fun getItemCount(): Int = mMainCategories.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainCategoryViewHolder {
        return MainCategoryViewHolder(LayoutInflater.from(parent?.context)
                .inflate(R.layout.view_main_category, parent, false))
    }

    override fun onBindViewHolder(holder: MainCategoryViewHolder?, position: Int) {
        if (holder == null) return

        val mainCategory = mMainCategories[position]
        holder.mIdTextView.text = mainCategory.id.toString()
        holder.mNameTextView.text = mainCategory.name
        holder.view.setOnClickListener { onItemClicked(holder) }
        holder.mDeleteButton.setOnClickListener { onDeleteButtonClicked(mainCategory, position) }
    }

    fun setMainCategories(mainCategories: List<MainCategory>) {
        mMainCategories = mainCategories.toMutableList()
    }

    fun removeAtPosition(position: Int) {
        if (position < itemCount) {
            mMainCategories.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    open fun onItemClicked(holder: MainCategoryViewHolder) {}

    open fun onDeleteButtonClicked(mainCategory: MainCategory, position: Int) {}
}