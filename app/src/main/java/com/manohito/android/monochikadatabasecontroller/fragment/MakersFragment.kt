package com.manohito.android.monochikadatabasecontroller.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import com.manohito.android.monochikadatabasecontroller.ApiService
import com.manohito.android.monochikadatabasecontroller.MonoChikaRetrofit
import com.manohito.android.monochikadatabasecontroller.R
import com.manohito.android.monochikadatabasecontroller.adapter.MakersListAdapter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MakersFragment : Fragment() {
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mMakerListView: ListView

    private val makerListAdapter: MakersListAdapter by lazy {
        MakersListAdapter(context)
    }

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater?.inflate(R.layout.fragment_makers, container, false)?.apply {
            mProgressBar = findViewById(R.id.makers_progress_bar)
            mMakerListView = findViewById<ListView>(R.id.makers_listview).apply {
                adapter = makerListAdapter
            }
        }
    }

    override fun onResume() {
        super.onResume()

        mMakerListView.visibility = View.GONE
        mProgressBar.visibility = View.VISIBLE

        MonoChikaRetrofit.retrofit.create(ApiService::class.java).getMakers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate {
                    mProgressBar.visibility = View.GONE
                    mMakerListView.visibility = View.VISIBLE
                }
                .subscribe({
                    Log.d("MakersFragment", it.toString())
                    makerListAdapter.setMakers(it)
                    makerListAdapter.notifyDataSetChanged()
                }, {
                    Toast.makeText(context, "エラー: $it", Toast.LENGTH_SHORT).show()
                })
    }
}