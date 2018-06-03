package com.example.srawa.mvpkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.example.srawa.mvpkotlin.database.AppDatabase
import com.example.srawa.mvpkotlin.database.department.Department
import com.example.srawa.mvpkotlin.database.department.DepartmentRepo
import com.example.srawa.mvpkotlin.database.department.DepartmentRepoImpl
import com.example.srawa.mvpkotlin.database.employee.Employee
import com.example.srawa.mvpkotlin.database.employee.EmployeeRepoImpl
import com.example.srawa.mvpkotlin.database.util.klaxon.KlaxonProductType
import com.example.srawa.mvpkotlin.database.product.Product
import com.example.srawa.mvpkotlin.database.util.klaxon.KlaxonProductTypeConverter
import com.example.srawa.mvpkotlin.util.Assets
import com.example.srawa.mvpkotlin.database.util.klaxon.KlaxonDateType
import com.example.srawa.mvpkotlin.database.util.klaxon.KlaxonDateTypeConverter
import com.example.srawa.mvpkotlin.util.DatabaseBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var mDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mDatabase = DatabaseBuilder.getDatabase(applicationContext)
        initDatabase()
        test()
    }

    fun initDatabase(){
       /* val products = Klaxon()
                .fieldConverter(KlaxonProductType::class, KlaxonProductTypeConverter)
                .parseArray<Product>(assets.open(Assets.ASSET_PRODUCT))
        products?.forEach {
            Log.d("xyz_products",it.type.toString())
        }
        val employees = Klaxon()
                .fieldConverter(KlaxonDateType::class, KlaxonDateTypeConverter)
                .parseArray<Employee>(assets.open(Assets.ASSET_EMPLOYEE))
        employees?.forEach {
            Log.d("xyz_employees",it.toString())
        }
        val departments = Klaxon()
                .parseArray<Department>(assets.open(Assets.ASSET_DEPARTMENT))
        departments?.forEach {
            Log.d("xyz_department",it.toString())
        }*/
        DatabaseBuilder.populateDatabase(applicationContext, mDatabase)

    }

    fun test(){
        val employeeRepo = EmployeeRepoImpl(mDatabase.employeeDao())
        employeeRepo.getEmployeesByName("Sou").subscribeOn(Schedulers.io()).subscribe {
            it.forEach {
                Log.d("xyz",it.toString())
            }
        }

    }

    override fun onDestroy() {
        mDatabase.close()
        super.onDestroy()
    }
}
