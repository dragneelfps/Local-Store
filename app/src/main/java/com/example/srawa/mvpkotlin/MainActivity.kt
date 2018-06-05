package com.example.srawa.mvpkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.srawa.mvpkotlin.ui.EmployeeListFragment
import com.example.srawa.mvpkotlin.util.DatabaseBuilder
import com.example.srawa.mvpkotlin.util.PerferenceHelper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = DatabaseBuilder.getDatabase(applicationContext)

        if (PerferenceHelper.isDbInitialized(applicationContext)) {
            Log.d("xyz", "DB IS ALREADY INITIALIZED")
        } else {
            Log.d("xyz", "INITIALIZING DB")
            DatabaseBuilder.populateDatabase(applicationContext, database)
            PerferenceHelper.dbInit(applicationContext)
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
            else ->
                return false
        }
        return true
    }

}
