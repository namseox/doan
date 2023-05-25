package com.kma.myapplication.utils

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager


fun Activity.hideKeyboard() {
    currentFocus?.let {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}


fun Activity.openWebsite(url: String) {
    try {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        this.startActivity(browserIntent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Activity.openPlayStore() {
    val appPackage = this.packageName
    try {
        this.startActivity(
            Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackage"))
        )
    } catch (e: ActivityNotFoundException) {
        this.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$appPackage")
            )
        )
    }
}