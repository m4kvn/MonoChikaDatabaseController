package com.manohito.android.monochikadatabasecontroller.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
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
import com.manohito.android.monochikadatabasecontroller.adapter.ShopsListAdapter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class ShopsFragment : Fragment() {
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mShopListView: ListView
    private lateinit var mShopListAdapter: ShopsListAdapter

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater?.inflate(R.layout.fragment_shops, container, false)?.apply {
            mProgressBar = findViewById(R.id.shops_progress_bar)

            mShopListAdapter = ShopsListAdapter(context)
            mShopListView = findViewById(R.id.shops_listview)
            mShopListView.adapter = mShopListAdapter

            findViewById<SwipeRefreshLayout>(R.id.shops_list_swipe).apply {
                setOnRefreshListener {
                    MonoChikaRetrofit.retrofit.create(ApiService::class.java).getShops()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doAfterTerminate {
                                isRefreshing = false
                            }
                            .subscribe({
                                Log.d("ShopsFragment", it.toString())
                                mShopListAdapter.setShops(it)
                                mShopListAdapter.notifyDataSetChanged()
                            }, {
                                Toast.makeText(context, "エラー: $it", Toast.LENGTH_SHORT).show()
                            })
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        mShopListView.visibility = View.GONE
        mProgressBar.visibility = View.VISIBLE

        MonoChikaRetrofit.retrofit.create(ApiService::class.java).getShops()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate {
                    mProgressBar.visibility = View.GONE
                    mShopListView.visibility = View.VISIBLE
                }
                .subscribe({
                    Log.d("ShopsFragment", it.toString())
                    mShopListAdapter.setShops(it)
                    mShopListAdapter.notifyDataSetChanged()
                }, {
                    Toast.makeText(context, "エラー: $it", Toast.LENGTH_SHORT).show()
                })
    }
}
