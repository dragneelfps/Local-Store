package com.example.srawa.mvpkotlin.ui.product

import com.example.srawa.mvpkotlin.database.AppDatabase

interface ProductListPresenter {

    fun searchProductByName(database: AppDatabase, name: String)

}