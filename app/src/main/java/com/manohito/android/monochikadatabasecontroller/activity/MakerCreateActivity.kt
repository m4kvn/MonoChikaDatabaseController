package com.manohito.android.monochikadatabasecontroller.activity

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.manohito.android.monochikadatabasecontroller.MonoChikaRetrofit
import com.manohito.android.monochikadatabasecontroller.R
import kotterknife.bindView
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MakerCreateActivity : AppCompatActivity() {
    private val mNameEditText: EditText by bindView(R.id.maker_create_name)
    private val mCreateButton: Button by bindView(R.id.maker_create)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maker_create)

        supportActionBar?.let {
            it.setHomeButtonEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }

        mCreateButton.setOnClickListener {
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                    .hideSoftInputFromWindow(currentFocus.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

            val name = mNameEditText.text.toString()

            MonoChikaRetrofit.getApi().createMaker(name)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Toast.makeText(applicationContext, "Create new maker", Toast.LENGTH_LONG).show()
                        setResult(MainActivity.RESULT_MAKERS)
                        finish()
                    }, {
                        Toast.makeText(applicationContext, "Error: $it", Toast.LENGTH_LONG).show()
                    })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                setResult(MainActivity.RESULT_MAKERS)
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
