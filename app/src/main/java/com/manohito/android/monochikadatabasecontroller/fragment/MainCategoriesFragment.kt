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
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.manohito.android.monochikadatabasecontroller.ActivityResult
import com.manohito.android.monochikadatabasecontroller.MonoChikaRetrofit
import com.manohito.android.monochikadatabasecontroller.R
import com.manohito.android.monochikadatabasecontroller.activity.MainCategoryCreateActivity_
import com.manohito.android.monochikadatabasecontroller.adapter.MainCategoryRecyclerAdapter
import com.manohito.android.monochikadatabasecontroller.model.MainCategory
import com.manohito.android.monochikadatabasecontroller.view.holder.MainCategoryViewHolder
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainCategoriesFragment : Fragment() {
    private lateinit var mContainer: View
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mRecyclerAdapter: MainCategoryRecyclerAdapter
    private lateinit var mCreateButton: FloatingActionButton

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater?.inflate(R.layout.fragment_main_categories, container, false)?.apply {
            mContainer = findViewById(R.id.main_categories_container)
            mProgressBar = findViewById(R.id.main_categories_progressBar)
            mRecyclerView = findViewById(R.id.main_categories_recycler)
            mRecyclerAdapter = object : MainCategoryRecyclerAdapter() {

                override fun onItemClicked(holder: MainCategoryViewHolder) {
                    holder.mExpandGroup.visibility = when (holder.mExpandGroup.visibility) {
                        View.VISIBLE -> View.GONE
                        else -> View.VISIBLE
                    }
                }

                override fun onDeleteButtonClicked(mainCategory: MainCategory, position: Int) {
                    MonoChikaRetrofit.getApi().deleteMainCategory(mainCategory.id)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                                removeAtPosition(position)
                                Snackbar.make(mContainer, "メインカテゴリーを削除しました", Snackbar.LENGTH_SHORT).show()
                            }, {
                                Log.d("MainCategoriesFragment", it.toString())
                                Toast.makeText(context, "エラー: $it", Toast.LENGTH_SHORT).show()
                            })
                }
            }
            mRecyclerView.adapter = mRecyclerAdapter

            val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            divider.setDrawable(ContextCompat.getDrawable(context, R.drawable.divider))
            mRecyclerView.addItemDecoration(divider)

            findViewById<SwipeRefreshLayout>(R.id.main_categories_swipe).apply {
                setOnRefreshListener {
                    MonoChikaRetrofit.getApi().getMainCategories()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doAfterTerminate { isRefreshing = false }
                            .subscribe {
                                Log.d("MainCategoriesFragment", it.toString())
                                mRecyclerAdapter.setMainCategories(it)
                                mRecyclerAdapter.notifyDataSetChanged()
                            }
                }
            }

            mCreateButton = findViewById(R.id.main_categories_create)
            mCreateButton.setOnClickListener {
                val intent = Intent(context, MainCategoryCreateActivity_::class.java)
                startActivityForResult(intent, ActivityResult.RESULT_MAIN_CATEGORIES)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    private fun getData() {
        mRecyclerView.visibility = View.GONE
        mProgressBar.visibility = View.VISIBLE

        MonoChikaRetrofit.getApi().getMainCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate {
                    mProgressBar.visibility = View.GONE
                    mRecyclerView.visibility = View.VISIBLE
                }
                .subscribe({
                    Log.d("MainCategoriesFragment", it.toString())
                    mRecyclerAdapter.setMainCategories(it)
                    mRecyclerAdapter.notifyDataSetChanged()
                }, {
                    Toast.makeText(context, "エラー: $it", Toast.LENGTH_LONG).show()
                })
    }
}