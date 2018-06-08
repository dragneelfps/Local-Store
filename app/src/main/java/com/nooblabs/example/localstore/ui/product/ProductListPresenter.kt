package com.nooblabs.example.localstore.ui.product

import com.nooblabs.example.localstore.database.AppDatabase

interface ProductListPresenter {

    fun searchProductByName(database: AppDatabase, name: String)

}