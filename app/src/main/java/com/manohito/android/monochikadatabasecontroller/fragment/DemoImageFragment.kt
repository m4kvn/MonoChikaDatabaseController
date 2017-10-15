package com.manohito.android.monochikadatabasecontroller.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.manohito.android.monochikadatabasecontroller.MonoChikaRetrofit
import com.manohito.android.monochikadatabasecontroller.R
import com.manohito.android.monochikadatabasecontroller.adapter.DemoImageRecyclerAdapter
import com.manohito.android.monochikadatabasecontroller.adapter.MakerRecyclerAdapter
import com.manohito.android.monochikadatabasecontroller.model.DemoImage
import com.manohito.android.monochikadatabasecontroller.model.Maker
import com.manohito.android.monochikadatabasecontroller.view.holder.DemoImageViewHolder
import com.manohito.android.monochikadatabasecontroller.view.holder.MakerViewHolder
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class DemoImageFragment : Fragment() {
    private lateinit var mDemoImageRecyclerView: RecyclerView
    private lateinit var mDemoImageRecyclerAdapter: DemoImageRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater?.inflate(R.layout.fragment_demo_image, container, false)?.apply {
            mDemoImageRecyclerView = findViewById(R.id.demo_image_recycler)
            mDemoImageRecyclerAdapter = object : DemoImageRecyclerAdapter(context) {
                override fun updateImageView(holder: DemoImageViewHolder, demoImage: DemoImage) {
                    holder.mProgress.visibility = VISIBLE
                    Picasso.with(context)
                            .load(demoImage.url)
                            .into(holder.mImage, object : Callback {
                                override fun onSuccess() {
                                    Log.d("DemoImageRecycler", "onSuccess")
                                    holder.mProgress.visibility = GONE
                                }
                                override fun onError() {
                                    Log.d("DemoImageRecycler", "onError")
                                    holder.mProgress.visibility = GONE
                                }
                            })
                }
            }
            mDemoImageRecyclerView.adapter = mDemoImageRecyclerAdapter

            val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            divider.setDrawable(ContextCompat.getDrawable(context, R.drawable.divider))
            mDemoImageRecyclerView.addItemDecoration(divider)
            mDemoImageRecyclerView.visibility = VISIBLE
        }
    }
}