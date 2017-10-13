package com.manohito.android.monochikadatabasecontroller.view

import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import com.manohito.android.monochikadatabasecontroller.R
import com.manohito.android.monochikadatabasecontroller.activity.MakerDetailActivity
import com.manohito.android.monochikadatabasecontroller.model.Maker

class MakersListView : ConstraintLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_maker, this)
    }

    fun setMakerListView(maker: Maker) {
        findViewById<TextView>(R.id.maker_list_maker_name_textview).apply {
            this.text = maker.name
        }
        setOnClickListener {
            context.startActivity(Intent(context, MakerDetailActivity::class.java))
        }
    }
}