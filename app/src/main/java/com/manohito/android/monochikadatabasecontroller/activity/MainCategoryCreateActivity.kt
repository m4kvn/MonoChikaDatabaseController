package com.manohito.android.monochikadatabasecontroller.activity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.manohito.android.monochikadatabasecontroller.ActivityResult
import com.manohito.android.monochikadatabasecontroller.MonoChikaRetrofit
import com.manohito.android.monochikadatabasecontroller.R
import com.manohito.android.monochikadatabasecontroller.Util
import kotterknife.bindView
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.OptionsItem
import org.androidannotations.annotations.SystemService
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

@SuppressLint("Registered")
@EActivity(R.layout.activity_main_category_create)
open class MainCategoryCreateActivity : AppCompatActivity() {

    private val mNameEditText: EditText by bindView(R.id.main_category_create_name)

    @SystemService
    lateinit var inputMethodManager: InputMethodManager

    @Click(R.id.main_category_create)
    fun updateCreateButtonClicked() {
        Util.hideKeyboard(inputMethodManager, currentFocus.windowToken)

        val name = mNameEditText.text.toString()

        MonoChikaRetrofit.getApi()
                .createMainCategory(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Util.showLongToast(applicationContext,
                            String.format(getString(R.string.create_new_data), "main category"))
                    finishActivity()
                }, {
                    Util.showLongToast(applicationContext, "Error: $it")
                })
    }

    @OptionsItem(android.R.id.home)
    fun onOptionMenuHomeSelected() {
        finishActivity()
    }

    private fun finishActivity() {
        setResult(ActivityResult.RESULT_MAIN_CATEGORY)
        finish()
    }
}
