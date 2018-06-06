package com.example.srawa.mvpkotlin.ui.department

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.srawa.mvpkotlin.R
import com.example.srawa.mvpkotlin.adapters.DepartmentListAdapter
import com.example.srawa.mvpkotlin.database.AppDatabase
import com.example.srawa.mvpkotlin.database.department.Department
import com.example.srawa.mvpkotlin.ui.base.BaseFragment
import com.example.srawa.mvpkotlin.util.DatabaseBuilder
import kotlinx.android.synthetic.main.fragment_department_list.*

class DepartmentListFragment : BaseFragment(), DepartmentListView {

    private lateinit var departmentListAdapter: DepartmentListAdapter
    private lateinit var mDatabase: AppDatabase
    private lateinit var presenter: DepartmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_department_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let { activity ->
            mDatabase = DatabaseBuilder.getDatabase(activity.applicationContext)
            init()
        }

    }

    fun init() {
        presenter = DepartmentPresenterImp(this)
        departmentListAdapter = DepartmentListAdapter()
        department_list.layoutManager = LinearLayoutManager(activity)
        department_list.adapter = departmentListAdapter
        presenter.loadAllDepartments(mDatabase)
    }


    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun setItems(items: List<Department>) {
        val searchResultSize = items.size
        when (searchResultSize) {
            0 -> {
                displayMessage("No results found")
            }
            else -> {
                displayMessage("$searchResultSize results found")
            }
        }
        departmentListAdapter.values = items
    }

    override fun displayMessage(message: String) {
        search_result.text = message
    }

    override fun clearItems() {
        displayMessage("")
        departmentListAdapter.values = emptyList()
    }
}