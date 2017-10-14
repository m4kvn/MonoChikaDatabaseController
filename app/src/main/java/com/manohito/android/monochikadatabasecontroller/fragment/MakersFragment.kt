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
import com.manohito.android.monochikadatabasecontroller.adapter.MakerRecyclerAdapter
import com.manohito.android.monochikadatabasecontroller.model.Maker
import com.manohito.android.monochikadatabasecontroller.view.holder.MakerViewHolder
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MakersFragment : Fragment() {
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mMakerRecyclerView: RecyclerView
    private lateinit var mMakerRecyclerAdapter: MakerRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater?.inflate(R.layout.fragment_makers, container, false)?.apply {
            mProgressBar = findViewById(R.id.makers_progress_bar)
            mMakerRecyclerView = findViewById(R.id.maker_recycler)
            mMakerRecyclerAdapter = object : MakerRecyclerAdapter() {
                override fun onItemClicked(holder: MakerViewHolder) {
                    holder.mExpandGroup.visibility = when (holder.mExpandGroup.visibility) {
                        VISIBLE -> GONE
                        else -> VISIBLE
                    }
                }

                override fun onDeleteButtonClicked(maker: Maker, position: Int) {
                    MonoChikaRetrofit.getApi().deleteMaker(maker.id)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                                removeAtPosition(position)
                            }, {
                                Log.d("MakersFragment", it.toString())
                                Toast.makeText(context, "エラー: $it", Toast.LENGTH_SHORT).show()
                            })
                }
            }
            mMakerRecyclerView.adapter = mMakerRecyclerAdapter

            val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            divider.setDrawable(ContextCompat.getDrawable(context, R.drawable.divider))
            mMakerRecyclerView.addItemDecoration(divider)

            findViewById<SwipeRefreshLayout>(R.id.makers_list_swipe).apply {
                setOnRefreshListener {
                    MonoChikaRetrofit.getApi().getMakers()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doAfterTerminate { isRefreshing = false }
                            .subscribe {
                                Log.d("MakersFragment", it.toString())
                                mMakerRecyclerAdapter.setMakers(it)
                                mMakerRecyclerAdapter.notifyDataSetChanged()
                            }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    private fun getData() {
        mMakerRecyclerView.visibility = GONE
        mProgressBar.visibility = VISIBLE

        MonoChikaRetrofit.getApi().getMakers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate {
                    mProgressBar.visibility = GONE
                    mMakerRecyclerView.visibility = VISIBLE
                }
                .subscribe({
                    Log.d("MakersFragment", it.toString())
                    mMakerRecyclerAdapter.setMakers(it)
                    mMakerRecyclerAdapter.notifyDataSetChanged()
                }, {
                    Toast.makeText(context, "エラー: $it", Toast.LENGTH_LONG).show()
                })
    }
}