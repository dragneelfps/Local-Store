package com.example.srawa.mvpkotlin.database.product

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface ProductDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: List<Product>)

    @Query("SELECT * FROM product")
    fun getAllProducts(): List<Product>

    @Query("SELECT * FROM product WHERE name LIKE '%' || :name || '%'")
    fun getProductsByName(name: String): List<Product>

    @Query("SELECT * FROM product WHERE dept_id = :deptId")
    fun getProductsByDeptId(deptId: Long): List<Product>

}