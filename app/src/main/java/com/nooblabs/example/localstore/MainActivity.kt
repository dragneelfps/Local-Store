package com.nooblabs.example.localstore

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.nooblabs.example.localstore.ui.department.DepartmentListFragment
import com.nooblabs.example.localstore.ui.employee.EmployeeListFragment
import com.nooblabs.example.localstore.ui.product.ProductListFragment
import com.nooblabs.example.localstore.util.DatabaseBuilder
import com.nooblabs.example.localstore.util.PerferenceHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = DatabaseBuilder.getDatabase(applicationContext)

        if (PerferenceHelper.isDbInitialized(applicationContext)) {
            Log.d("xyz", "DB IS ALREADY INITIALIZED")
            init()
        } else {
            Log.d("xyz", "INITIALIZING DB")
            DatabaseBuilder.populateDatabase(applicationContext, database)
            PerferenceHelper.dbInit(applicationContext)
            init()
        }
    }

    fun init() {
        nav_view.setNavigationItemSelectedListener { item ->
            item.isChecked = true
            drawer_layout.closeDrawers()

            when (item.itemId) {
                R.id.employee_search_item ->
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout, EmployeeListFragment()).commit()
                R.id.product_search_item ->
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout, ProductListFragment()).commit()
                R.id.departments_list_item ->
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout, DepartmentListFragment()).commit()
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.employee_search_item ->
                supportFragmentManager.beginTransaction().replace(R.id.frame_layout, EmployeeListFragment()).commit()
            R.id.product_search_item ->
                supportFragmentManager.beginTransaction().replace(R.id.frame_layout, ProductListFragment()).commit()
            R.id.departments_list_item ->
                supportFragmentManager.beginTransaction().replace(R.id.frame_layout, DepartmentListFragment()).commit()
            else ->
                return false
        }
        return true
    }

}
