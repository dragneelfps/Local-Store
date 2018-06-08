package com.nooblabs.example.localstore.ui.departmentdetail.product

import com.nooblabs.example.localstore.database.AppDatabase
import com.nooblabs.example.localstore.database.product.ProductRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductListPresenterImp(var mainView: ProductListView) : ProductListPresenter {

    override fun loadAll(database: AppDatabase, deptId: Long) {
        if (mainView.getRootView() != null) {
            mainView.clearItems()
            mainView.showProgress()

            val repo = ProductRepoImpl(database.productDao())
            repo.getProductsByDeptId(deptId)
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        if (mainView.getRootView() != null) {
                            mainView.setItems(items = it)
                            mainView.hideProgress()
                        }
                    }
        }
    }
}