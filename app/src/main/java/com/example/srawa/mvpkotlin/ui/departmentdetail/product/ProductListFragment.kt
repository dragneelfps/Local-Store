package com.example.srawa.mvpkotlin.ui.departmentdetail.product

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.srawa.mvpkotlin.R
import com.example.srawa.mvpkotlin.adapters.DepartmentProductListAdapter
import com.example.srawa.mvpkotlin.database.AppDatabase
import com.example.srawa.mvpkotlin.database.product.Product
import com.example.srawa.mvpkotlin.ui.base.BaseFragment
import com.example.srawa.mvpkotlin.ui.departmentdetail.base.ItemsListView
import com.example.srawa.mvpkotlin.util.DatabaseBuilder
import kotlinx.android.synthetic.main.fragment_department_product_list.*

class ProductListFragment : BaseFragment(), ProductListView {

    override var deptId: Long = -1

    private lateinit var mDatabase: AppDatabase
    private lateinit var adapter: DepartmentProductListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_department_product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            mDatabase = DatabaseBuilder.getDatabase(it)
            init()
            loadData()
        }
    }

    private lateinit var presenter: ProductListPresenter

    fun init() {
        deptId = arguments?.getLong(ItemsListView.DEPT_ID, -1) ?: -1
        presenter = ProductListPresenterImp(this)
        adapter = DepartmentProductListAdapter()
        product_list.layoutManager = LinearLayoutManager(activity)
        product_list.adapter = adapter
    }


    fun loadData() {
        presenter.loadAll(mDatabase, deptId)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun setItems(items: List<Product>) {
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
        searchResult.text = message
    }

    override fun clearItems() {
        displayMessage("")
        adapter.values = emptyList()
    }
}