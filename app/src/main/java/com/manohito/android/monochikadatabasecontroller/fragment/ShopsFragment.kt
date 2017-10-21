package com.manohito.android.monochikadatabasecontroller.fragment


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
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
import com.manohito.android.monochikadatabasecontroller.activity.MainActivity
import com.manohito.android.monochikadatabasecontroller.activity.ShopCreateActivity_
import com.manohito.android.monochikadatabasecontroller.adapter.ShopRecyclerAdapter
import com.manohito.android.monochikadatabasecontroller.model.Shop
import com.manohito.android.monochikadatabasecontroller.view.holder.ShopViewHolder
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class ShopsFragment : Fragment() {
    private lateinit var mContainer: View
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mShopRecyclerView: RecyclerView
    private lateinit var mShopRecyclerAdapter: ShopRecyclerAdapter
    private lateinit var mCreateButton: FloatingActionButton

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater?.inflate(R.layout.fragment_shops, container, false)?.apply {
            mContainer = findViewById(R.id.shops_container)
            mProgressBar = findViewById(R.id.shops_progress_bar)
            mShopRecyclerView = findViewById(R.id.shop_recycler)

            mShopRecyclerAdapter = object : ShopRecyclerAdapter() {
                override fun onItemClicked(holder: ShopViewHolder) {
                    holder.mExpandGroup.visibility = when (holder.mExpandGroup.visibility) {
                        VISIBLE -> GONE
                        else -> VISIBLE
                    }
                }

                override fun onDeleteButtonClicked(shop: Shop, position: Int) {
                    MonoChikaRetrofit.getApi().deleteShop(shop.id)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                                removeAtPosition(position)
                                Snackbar.make(mContainer, "店舗を削除しました", Snackbar.LENGTH_SHORT).show()
                            }, {
                                Log.d("ShopsFragment", it.toString())
                                Toast.makeText(context, "Error: $it", Toast.LENGTH_SHORT).show()
                            })
                }
            }

            mShopRecyclerView.adapter = mShopRecyclerAdapter

            val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            divider.setDrawable(ContextCompat.getDrawable(context, R.drawable.divider))
            mShopRecyclerView.addItemDecoration(divider)

            findViewById<SwipeRefreshLayout>(R.id.shops_list_swipe).apply {
                setOnRefreshListener {
                    MonoChikaRetrofit.getApi().getShops()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doAfterTerminate { isRefreshing = false }
                            .subscribe {
                                Log.d("ShopsFragment", it.toString())
                                mShopRecyclerAdapter.setShops(it)
                                mShopRecyclerAdapter.notifyDataSetChanged()
                            }
                }
            }

            mCreateButton = findViewById(R.id.shop_detail_create)
            mCreateButton.setOnClickListener {
                val intent = Intent(context, ShopCreateActivity_::class.java)
                startActivityForResult(intent, MainActivity.RESULT_SHOPS)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    private fun getData() {
        mShopRecyclerView.visibility = GONE
        mProgressBar.visibility = VISIBLE

        MonoChikaRetrofit.getApi().getShops()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate {
                    mProgressBar.visibility = GONE
                    mShopRecyclerView.visibility = VISIBLE
                }
                .subscribe({
                    Log.d("ShopsFragment", it.toString())
                    mShopRecyclerAdapter.setShops(it)
                    mShopRecyclerAdapter.notifyDataSetChanged()
                }, {
                    Toast.makeText(context, "エラー: $it", Toast.LENGTH_SHORT).show()
                })
    }
}
