package com.example.srawa.mvpkotlin.ui

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.srawa.mvpkotlin.R
import com.example.srawa.mvpkotlin.adapters.EmployeeListAdapter
import com.example.srawa.mvpkotlin.database.AppDatabase
import com.example.srawa.mvpkotlin.util.DatabaseBuilder
import com.example.srawa.mvpkotlin.util.PerferenceHelper
import kotlinx.android.synthetic.main.activity_employee_list.*

class EmployeeListActivity : AppCompatActivity(), EmployeeListView {

    private lateinit var presenter: EmployeeListPresenter

    private lateinit var employeeListAdapter: EmployeeListAdapter

    private lateinit var mDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_list)
        mDatabase = DatabaseBuilder.getDatabase(applicationContext)

        if(PerferenceHelper.isDbInitialized(this)){
            Log.d("xyz","DB IS ALREADY INITIALIZED")
            init()
        }else{
            Log.d("xyz","INITIALIZING DB")
            DatabaseBuilder.populateDatabase(applicationContext, mDatabase)
            PerferenceHelper.dbInit(this)
            init()
        }

    }

    fun init(){
        employeeListAdapter = EmployeeListAdapter()

        presenter = EmployeeListPresenterImpl(this)

        employee_list.layoutManager = LinearLayoutManager(this)
        employee_list.adapter = employeeListAdapter
        search_emp_btn.setOnClickListener {
            hideSoftKeyboard()
            val searchName = emp_name_search_term.text.toString()
            presenter.searchEmployeesByName(mDatabase, searchName)
        }
    }

    fun hideSoftKeyboard() {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(currentFocus.windowToken, 0)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun setItems(employees: List<EmployeeListView.EmployeeDetail>) {
        employeeListAdapter.values = employees
    }

    override fun clearItems() {
        employeeListAdapter.values = emptyList()
    }

    override fun displayMessage(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
