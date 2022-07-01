package com.example.smartparking.base

import android.app.Activity
import android.view.Gravity
import android.widget.Toast
import com.example.smartparking.R

class CustomToast(var context: Activity) {
    fun showToast() {
        val layout = context.layoutInflater.inflate(R.layout.custom_toast, null)
        val customToast = Toast(context)
        customToast.duration = Toast.LENGTH_SHORT
        customToast.setGravity(Gravity.BOTTOM, 0, 0)
        customToast.view = layout
        customToast.show()
    }
}

