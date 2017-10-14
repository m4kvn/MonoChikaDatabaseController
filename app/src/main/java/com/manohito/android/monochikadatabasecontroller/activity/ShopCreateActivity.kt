package com.manohito.android.monochikadatabasecontroller.activity

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.manohito.android.monochikadatabasecontroller.MonoChikaRetrofit
import com.manohito.android.monochikadatabasecontroller.R
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ShopCreateActivity : AppCompatActivity() {
    private val mShopName: EditText by lazy { findViewById<EditText>(R.id.shop_create_name) }
    private val mShopLatitude: EditText by lazy { findViewById<EditText>(R.id.shop_create_latitude) }
    private val mShopLongitude: EditText by lazy { findViewById<EditText>(R.id.shop_create_longitude) }
    private val mShopAddress: EditText by lazy { findViewById<EditText>(R.id.shop_create_address) }
    private val mCreateButton: Button by lazy { findViewById<Button>(R.id.shop_create) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_create)

        supportActionBar?.let {
            it.setHomeButtonEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }

        mCreateButton.setOnClickListener {
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                    .hideSoftInputFromWindow(currentFocus.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

            val name = mShopName.text.toString()
            val latitude = mShopLatitude.text.toString().toFloatOrNull()
            val longitude = mShopLongitude.text.toString().toFloatOrNull()
            val address = mShopAddress.text.toString()

            MonoChikaRetrofit.getApi().createShops(name, latitude ?: 0f, longitude ?: 0f, address)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Toast.makeText(applicationContext, "Create new shop", Toast.LENGTH_LONG).show()
                        setResult(MainActivity.RESULT_SHOPS)
                        finish()
                    }, {
                        Toast.makeText(applicationContext, "Error: $it", Toast.LENGTH_LONG).show()
                    })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                setResult(MainActivity.RESULT_SHOPS)
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
