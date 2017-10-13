package com.manohito.android.monochikadatabasecontroller.animation

import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation

class ResizeAnimation(private val targetView: View,
                      private val startHeight: Int,
                      private val addHeight: Int) : Animation() {

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        val newHeight = (startHeight + addHeight * interpolatedTime).toInt()
        targetView.layoutParams.height = newHeight
        targetView.requestLayout()
    }

    override fun willChangeBounds(): Boolean = true
}