package com.nooblabs.example.localstore.ui.base

import android.content.Context
import android.support.v4.app.Fragment
import android.view.inputmethod.InputMethodManager

abstract class BaseFragment : Fragment() {

    protected fun hideSoftKeyboard() {
        (activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
    }

}