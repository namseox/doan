package com.kma.myapplication.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

@SuppressLint("ClickableViewAccessibility")
fun Fragment.hideKeyboardOnClickOutside(view: View){
    if (view !is SearchView) {
        view.setOnTouchListener { _, _ ->
            activity?.hideKeyboard()
            false
        }
    }

    if (view is ViewGroup) {
        for (i in 0 until view.childCount) {
            val child = view.getChildAt(i)
            hideKeyboardOnClickOutside(child)
        }
    }
}

fun showKeyBoard(context: Context, v: View) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT)
}

@SuppressLint("ClickableViewAccessibility")
fun SearchView.clearFocusOnClickOutside(view: View){
    if (view !is SearchView) {
        view.setOnTouchListener { _, _ ->
            Log.e("----", "clearFocusOnClickOutside:")
            clearFocus()
            false
        }
    }

    if (view is ViewGroup) {
        for (i in 0 until view.childCount) {
            val child = view.getChildAt(i)
            clearFocusOnClickOutside(child)
        }
    }
}

@Suppress("DEPRECATION")
fun Fragment.setFullScreen() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        requireActivity().window.insetsController?.let {
            it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    } else {
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}

@Suppress("DEPRECATION")
fun Fragment.setDefaultScreen() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        requireActivity().window.insetsController?.let {
//            it.show(WindowInsets.Type.statusBars())
//            it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    } else {
        requireActivity().window.clearFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}

fun <T> Fragment.getNavigationResult(key: String = "keyPopBackTack") =
    findNavController().currentBackStackEntry?.savedStateHandle?.get<T>(key)

fun <T> Fragment.getNavigationResultLiveData(key: String = "keyPopBackTack") =
    findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)

fun <T> Fragment.setNavigationResult(result: T, key: String = "keyPopBackTack") {
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key, result)
}
