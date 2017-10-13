package com.manohito.android.monochikadatabasecontroller.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.manohito.android.monochikadatabasecontroller.model.Maker
import com.manohito.android.monochikadatabasecontroller.view.MakersListView

class MakersListAdapter(private val context: Context) : BaseAdapter() {
    private var makers: List<Maker> = emptyList()

    override fun getView(position: Int, currentView: View?, parent: ViewGroup?): View {
        return ((currentView as? MakersListView) ?: MakersListView(context)).apply {
            setMakerListView(makers[position])
        }
    }

    override fun getItem(position: Int): Any = makers[position]

    override fun getItemId(id: Int): Long = 0

    override fun getCount(): Int = makers.size

    fun setMakers(makers: List<Maker>) {
        this.makers = makers
    }
}