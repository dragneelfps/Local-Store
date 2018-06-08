package com.example.srawa.mvpkotlin.ui.base

interface MvpListView<T> : BaseMvpView {

    fun showProgress()

    fun hideProgress()

    fun setItems(items: List<T>)

    fun displayMessage(message: String)

    fun clearItems()
}