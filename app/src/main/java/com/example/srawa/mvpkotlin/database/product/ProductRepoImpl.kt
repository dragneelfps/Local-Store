package com.example.srawa.mvpkotlin.database.product

import io.reactivex.Completable
import io.reactivex.Observable

class ProductRepoImpl(var productDao: ProductDao): ProductRepo {

    override fun insertAll(products: List<Product>): Completable =
        Completable.create { emitter ->
            productDao.insertAll(products)
            emitter.onComplete()
        }

    override fun getAllProducts(): Observable<List<Product>> =
        Observable.fromCallable {
            productDao.getAllProducts()
        }

    override fun getProductsByName(name: String): Observable<List<Product>> =
        Observable.fromCallable {
            productDao.getProductsByName(name)
        }

    override fun getProductsByDeptId(deptId: Long): Observable<List<Product>> =
        Observable.fromCallable {
            productDao.getProductsByDeptId(deptId)
        }
}