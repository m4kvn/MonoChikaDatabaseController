package com.manohito.android.monochikadatabasecontroller

import android.content.Context
import android.os.IBinder
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

class Util {
    companion object {

        fun hideKeyboard(inputMethodManager: InputMethodManager, windowToken: IBinder) {
            inputMethodManager.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }

        fun showLongToast(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }
}