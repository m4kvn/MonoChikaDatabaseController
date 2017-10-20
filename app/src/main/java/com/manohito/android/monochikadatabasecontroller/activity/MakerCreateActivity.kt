package com.manohito.android.monochikadatabasecontroller.activity

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
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
@EActivity(R.layout.activity_maker_create)
open class MakerCreateActivity : AppCompatActivity() {

    private val mNameEditText: EditText by bindView(R.id.maker_create_name)

    @SystemService
    lateinit var inputMethodManager: InputMethodManager

    @Click(R.id.maker_create)
    fun updateCreateButtonClicked() {
        Util.hideKeyboard(inputMethodManager, currentFocus.windowToken)

        val name = mNameEditText.text.toString()

        MonoChikaRetrofit.getApi()
                .createMaker(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Util.showLongToast(applicationContext,
                            String.format(getString(R.string.create_new_data), "maker"))
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
        setResult(MainActivity.RESULT_MAKERS)
        finish()
    }
}
