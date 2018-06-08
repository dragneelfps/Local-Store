package com.nooblabs.example.localstore.ui.product

import com.nooblabs.example.localstore.database.AppDatabase
import com.nooblabs.example.localstore.database.product.ProductRepo
import com.nooblabs.example.localstore.database.product.ProductRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductListPresenterImpl(var productListView: ProductListView) : ProductListPresenter {

    override fun searchProductByName(database: AppDatabase, name: String) {
        if (productListView.getRootView() != null) {
            productListView.clearItems()
            productListView.showProgress()

            val productRepo: ProductRepo = ProductRepoImpl(database.productDao())
            productRepo.getProductsByName(name).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        if (productListView.getRootView() != null) {
                            productListView.setItems(items = it)
                            productListView.hideProgress()
                        }
                    }
        }
    }
}