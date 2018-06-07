package com.example.srawa.mvpkotlin.ui.departmentdetail.employee

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.srawa.mvpkotlin.R
import com.example.srawa.mvpkotlin.adapters.DepartmentEmployeeListAdapter
import com.example.srawa.mvpkotlin.database.AppDatabase
import com.example.srawa.mvpkotlin.database.employee.Employee
import com.example.srawa.mvpkotlin.ui.base.BaseFragment
import com.example.srawa.mvpkotlin.util.DatabaseBuilder
import kotlinx.android.synthetic.main.fragment_department_employee_list.*

class EmployeeListFragment : BaseFragment(), EmployeeListView {

    override var deptId: Long = -1
    private lateinit var presenter: EmployeeListPresenter
    private lateinit var adapter: DepartmentEmployeeListAdapter
    private lateinit var mDatabase: AppDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_department_employee_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            mDatabase = DatabaseBuilder.getDatabase(it)
            init()
            loadData()
        }
    }

    fun init() {
        deptId = arguments?.getLong(EmployeeListView.DEPT_ID, -1) ?: -1
        presenter = EmployeeListPresenterImp(this)
        adapter = DepartmentEmployeeListAdapter()
        department_employee_list.layoutManager = LinearLayoutManager(activity)
        department_employee_list.adapter = adapter
    }

    fun loadData() {
        Log.d("xyz", "Loading data")
        presenter.loadAllEmployeesWithDeptId(mDatabase, deptId)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun setItems(items: List<Employee>) {
        val searchResultSize = items.size
        when (searchResultSize) {
            0 -> {
                displayMessage("No results found")
            }
            else -> {
                displayMessage("$searchResultSize results found")
            }
        }
        adapter.values = items
    }

    override fun displayMessage(message: String) {
        search_result.text = message
    }

    override fun clearItems() {
        displayMessage("")
        adapter.values = emptyList()
    }
}