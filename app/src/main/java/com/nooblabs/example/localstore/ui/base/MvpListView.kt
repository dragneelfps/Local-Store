package com.nooblabs.example.localstore.ui.base

interface MvpListView<T> : BaseMvpView {

    fun showProgress()

    fun hideProgress()

    fun setItems(items: List<T>)

    fun displayMessage(message: String)

    fun clearItems()
}