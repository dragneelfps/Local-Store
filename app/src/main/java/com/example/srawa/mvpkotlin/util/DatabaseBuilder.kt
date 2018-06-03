package com.example.srawa.mvpkotlin.util

import android.arch.persistence.room.Room
import android.content.Context
import android.os.AsyncTask
import com.beust.klaxon.Klaxon
import com.example.srawa.mvpkotlin.database.AppDatabase
import com.example.srawa.mvpkotlin.database.department.Department
import com.example.srawa.mvpkotlin.database.department.DepartmentRepoImpl
import com.example.srawa.mvpkotlin.database.employee.Employee
import com.example.srawa.mvpkotlin.database.employee.EmployeeRepoImpl
import com.example.srawa.mvpkotlin.database.product.Product
import com.example.srawa.mvpkotlin.database.product.ProductRepoImpl
import com.example.srawa.mvpkotlin.database.util.klaxon.KlaxonDateType
import com.example.srawa.mvpkotlin.database.util.klaxon.KlaxonDateTypeConverter
import com.example.srawa.mvpkotlin.database.util.klaxon.KlaxonProductType
import com.example.srawa.mvpkotlin.database.util.klaxon.KlaxonProductTypeConverter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object DatabaseBuilder{


    private var DATABASE: AppDatabase? = null

    fun getDatabase(context: Context) =
        DATABASE ?: Room.databaseBuilder(context, AppDatabase::class.java, "supermarket").fallbackToDestructiveMigration().build()

    private lateinit var klaxon: Klaxon

    fun populateDatabase(context: Context, database: AppDatabase){
        klaxon = Klaxon()
        populateDepartments(context, database)
        populateEmployees(context, database)
        populateProduct(context, database)
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