package com.example.srawa.mvpkotlin.ui.employee

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.example.srawa.mvpkotlin.R
import com.example.srawa.mvpkotlin.adapters.EmployeeListAdapter
import com.example.srawa.mvpkotlin.database.AppDatabase
import com.example.srawa.mvpkotlin.util.DatabaseBuilder
import kotlinx.android.synthetic.main.fragment_employee_list.*

class EmployeeListFragment : android.support.v4.app.Fragment(), EmployeeListView {

    private lateinit var presenter: EmployeeListPresenter

    private lateinit var employeeListAdapter: EmployeeListAdapter

    private lateinit var mDatabase: AppDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_employee_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let { activity ->
            mDatabase = DatabaseBuilder.getDatabase(activity.applicationContext)
            init()
        }
    }

    fun init() {
        employeeListAdapter = EmployeeListAdapter()
        presenter = EmployeeListPresenterImpl(this)
        product_list.layoutManager = LinearLayoutManager(activity?.applicationContext)
        product_list.adapter = employeeListAdapter
        search_emp_btn.setOnClickListener {
            hideSoftKeyboard()
            val searchName = product_name_search_term.text.toString()
            presenter.searchEmployeesByName(mDatabase, searchName)
        }
    }

    fun hideSoftKeyboard() {
        (activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun setItems(items: List<EmployeeListView.EmployeeDetail>) {
        val searchResultSize = items.size
        when (searchResultSize) {
            0 -> {
                displayMessage("No results found")
            }
            else -> {
                displayMessage("$searchResultSize results found")
            }
        }
        employeeListAdapter.values = items
    }

    override fun clearItems() {
        displayMessage("")
        employeeListAdapter.values = emptyList()
    }

    override fun displayMessage(message: String) {
        search_result.text = message
    }

}
