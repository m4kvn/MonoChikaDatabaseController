package com.manohito.android.monochikadatabasecontroller.activity

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
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
@EActivity(R.layout.activity_shop_create)
open class ShopCreateActivity : AppCompatActivity() {
    private val mShopName: EditText by bindView(R.id.shop_create_name)
    private val mShopLatitude: EditText by bindView(R.id.shop_create_latitude)
    private val mShopLongitude: EditText by bindView(R.id.shop_create_longitude)
    private val mShopAddress: EditText by bindView(R.id.shop_create_address)

    @SystemService
    lateinit var inputMethodManager: InputMethodManager

    @Click(R.id.shop_create)
    fun updateCreateButtonClicked() {
        Util.hideKeyboard(inputMethodManager, currentFocus.windowToken)

        val name = mShopName.text.toString()
        val latitude = mShopLatitude.text.toString().toFloatOrNull() ?: 0f
        val longitude = mShopLongitude.text.toString().toFloatOrNull() ?: 0f
        val address = mShopAddress.text.toString()

        MonoChikaRetrofit.getApi()
                .createShops(name, latitude, longitude, address)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Util.showLongToast(applicationContext,
                            String.format(getString(R.string.create_new_data), "shop"))
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
        setResult(ActivityResult.RESULT_SHOPS)
        finish()
    }
}
