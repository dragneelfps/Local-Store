package com.example.srawa.mvpkotlin.ui.departmentdetail.product

import com.example.srawa.mvpkotlin.database.AppDatabase
import com.example.srawa.mvpkotlin.database.product.ProductRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductListPresenterImp(var mainView: ProductListView) : ProductListPresenter {

    override fun loadAll(database: AppDatabase, deptId: Long) {
        mainView.clearItems()
        mainView.showProgress()

        val repo = ProductRepoImpl(database.productDao())
        repo.getProductsByDeptId(deptId)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    mainView.setItems(items = it)
                    mainView.hideProgress()
                }

    }
}