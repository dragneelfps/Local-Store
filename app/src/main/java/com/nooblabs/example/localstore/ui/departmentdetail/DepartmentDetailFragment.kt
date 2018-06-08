package com.nooblabs.example.localstore.ui.departmentdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nooblabs.example.localstore.R
import com.nooblabs.example.localstore.database.AppDatabase
import com.nooblabs.example.localstore.ui.base.BaseFragment
import com.nooblabs.example.localstore.util.DatabaseBuilder
import kotlinx.android.synthetic.main.fragment_department_detail.*

class DepartmentDetailFragment : BaseFragment(), DepartmentDetailView {

    override var deptId: Long = -1
    private lateinit var mDatabase: AppDatabase
    private lateinit var presenter: DepartmentDetailPresenter
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_department_detail, container, false)
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
        deptId = arguments?.getLong(DepartmentDetailView.DEPT_ID, -1) ?: -1
        presenter = DepartmentDetailPresenterImp(this)
        viewPagerAdapter = ViewPagerAdapter(childFragmentManager, deptId)
        view_pager.adapter = viewPagerAdapter
        tabs.setupWithViewPager(view_pager)
    }

    fun loadData() {
        presenter.loadDepartmentDetail(mDatabase, deptId)
    }

    override fun getRootView() = view

    override fun setDepartmentDetails(department: DepartmentDetailView.DepartmentDetail) {
        department.let {
            dept_name?.text = it.department.name
            dept_floor?.text = it.department.floor.toString()
            emp_count?.text = it.employeeCount.toString()
            product_count?.text = it.productCount.toString()
        }
    }
}