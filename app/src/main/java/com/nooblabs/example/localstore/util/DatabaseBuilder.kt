package com.nooblabs.example.localstore.util

import android.arch.persistence.room.Room
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.beust.klaxon.Klaxon
import com.nooblabs.example.localstore.database.AppDatabase
import com.nooblabs.example.localstore.database.department.Department
import com.nooblabs.example.localstore.database.department.DepartmentRepoImpl
import com.nooblabs.example.localstore.database.employee.Employee
import com.nooblabs.example.localstore.database.employee.EmployeeRepoImpl
import com.nooblabs.example.localstore.database.product.Product
import com.nooblabs.example.localstore.database.product.ProductRepoImpl
import com.nooblabs.example.localstore.database.util.klaxon.KlaxonDateType
import com.nooblabs.example.localstore.database.util.klaxon.KlaxonDateTypeConverter
import com.nooblabs.example.localstore.database.util.klaxon.KlaxonProductType
import com.nooblabs.example.localstore.database.util.klaxon.KlaxonProductTypeConverter
import io.reactivex.schedulers.Schedulers

object DatabaseBuilder{


    private var DATABASE: AppDatabase? = null

    fun getDatabase(context: Context) =
        DATABASE ?: Room.databaseBuilder(context, AppDatabase::class.java, "supermarket").fallbackToDestructiveMigration().build()

    private lateinit var klaxon: Klaxon

    fun populateDatabase(context: Context, database: AppDatabase){
        AsyncTask.execute {
            klaxon = Klaxon()
            populateDepartments(context, database)
            populateEmployees(context, database)
            populateProduct(context, database)
            Log.d("xyz", "DB INITIALIZED")
        }
    }

    fun populateDepartments(context: Context, database: AppDatabase){
        val departments = klaxon
                .parseArray<Department>(context.assets.open(Assets.ASSET_DEPARTMENT)) ?: emptyList()
        val repo = DepartmentRepoImpl(database.departmentDao())
        repo.insertAll(departments).subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread()).subscribe()
    }

    fun populateEmployees(context: Context, database: AppDatabase){

        val employees = klaxon
                .fieldConverter(KlaxonDateType::class, KlaxonDateTypeConverter)
                .parseArray<Employee>(context.assets.open(Assets.ASSET_EMPLOYEE)) ?: emptyList()
        val repo = EmployeeRepoImpl(database.employeeDao())
        repo.insertAll(employees).subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread()).subscribe()
    }

    fun populateProduct(context: Context, database: AppDatabase){
        val products = klaxon
                .fieldConverter(KlaxonProductType::class, KlaxonProductTypeConverter)
                .parseArray<Product>(context.assets.open(Assets.ASSET_PRODUCT)) ?: emptyList()
        val repo = ProductRepoImpl(database.productDao())
        repo.insertAll(products).subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread()).subscribe()
    }

}