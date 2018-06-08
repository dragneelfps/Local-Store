package com.nooblabs.example.localstore.database.product

import io.reactivex.Completable
import io.reactivex.Observable

interface ProductRepo {

    fun insertAll(products: List<Product>): Completable

    fun getAllProducts(): Observable<List<Product>>

    fun getProductsByName(name: String): Observable<List<Product>>

    fun getProductsByDeptId(deptId: Long): Observable<List<Product>>
}