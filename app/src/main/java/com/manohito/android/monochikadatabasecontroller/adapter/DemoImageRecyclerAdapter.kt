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
            DemoImage("https://www.evernote.com/l/As3SQEXc2OVLipoEuilsmZLqBvv5iAFxodAB/image.jpg"),
            DemoImage("https://pbs.twimg.com/media/DCecxrXUwAAHi8g.jpg"),
            DemoImage("http://xn--eck5eb7eb2541d26fovj.biz/wp-content/uploads/2015/02/927c75bc-1024x576.jpg"),
            DemoImage("https://scontent.cdninstagram.com/t51.2885-15/s320x320/e35/17265895_394009677624137_5672826696143732736_n.jpg"),
            DemoImage("https://rr.img.naver.jp/mig?src=http%3A%2F%2Fimgcc.naver.jp%2Fkaze%2Fmission%2FUSER%2F20160216%2F79%2F7640269%2F18%2F499x338xa35f11a9f491832a3a4b0114.jpg%2F300%2F600&twidth=300&theight=600&qlt=80&res_format=jpg&op=r")
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