package com.manohito.android.monochikadatabasecontroller.activity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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
    private val mCreateButton: Button by bindView(R.id.main_category_create)

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_category_create)

        supportActionBar?.let {
            it.setHomeButtonEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }

        mCreateButton.setOnClickListener {
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                    .hideSoftInputFromWindow(currentFocus.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

            val name = mNameEditText.text.toString()

            MonoChikaRetrofit.getApi().createMainCategory(name)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Toast.makeText(applicationContext, "Create new main_category", Toast.LENGTH_LONG).show()
                        setResult(MainActivity.RESULT_MAIN_CATEGORY)
                        finish()
                    }, {
                        Toast.makeText(applicationContext, "Error: $it", Toast.LENGTH_LONG).show()
                    })
        }
    }

    @OptionsItem(android.R.id.home)
    fun onOptionMenuHomeSelected() {
        finishActivity()
    }

    private fun finishActivity() {
        setResult(MainActivity.RESULT_MAIN_CATEGORY)
        finish()
    }
}
