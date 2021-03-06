package com.nooblabs.example.localstore.ui.product

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nooblabs.example.localstore.R
import com.nooblabs.example.localstore.adapters.ProductListAdapter
import com.nooblabs.example.localstore.database.AppDatabase
import com.nooblabs.example.localstore.database.product.Product
import com.nooblabs.example.localstore.ui.base.BaseFragment
import com.nooblabs.example.localstore.util.DatabaseBuilder
import kotlinx.android.synthetic.main.fragment_product_list.*

class ProductListFragment : BaseFragment(), ProductListView {

    private lateinit var mDatabase: AppDatabase
    private lateinit var productListAdapter: ProductListAdapter
    private lateinit var presenter: ProductListPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            mDatabase = DatabaseBuilder.getDatabase(it.applicationContext)
            init()
            loadInitData()
        }
    }

    fun init() {
        productListAdapter = ProductListAdapter()
        product_list.layoutManager = LinearLayoutManager(activity)
        product_list.adapter = productListAdapter
        presenter = ProductListPresenterImpl(this)
        search_product_btn.setOnClickListener {
            hideSoftKeyboard()
            val searchTerm = product_name_search_term.text.toString()
            presenter.searchProductByName(mDatabase, searchTerm)
        }
    }

    fun loadInitData() {
        presenter.searchProductByName(mDatabase, "")
    }

    override fun getRootView() = view

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun setItems(items: List<Product>) {
        val resultSize = items.size
        when (resultSize) {
            0 -> {
                displayMessage("No results found")
            }
            else -> {
                displayMessage("$resultSize results found")
            }
        }
        productListAdapter.values = items
    }

    override fun displayMessage(message: String) {
        search_result.text = message
    }

    override fun clearItems() {
        displayMessage("")
        productListAdapter.values = emptyList()
    }
}