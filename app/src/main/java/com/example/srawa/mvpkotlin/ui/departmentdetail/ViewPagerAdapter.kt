package com.example.srawa.mvpkotlin.ui.departmentdetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.srawa.mvpkotlin.ui.departmentdetail.base.ItemsListView
import com.example.srawa.mvpkotlin.ui.departmentdetail.employee.EmployeeListFragment
import com.example.srawa.mvpkotlin.ui.departmentdetail.product.ProductListFragment

class ViewPagerAdapter(fm: FragmentManager, private var deptId: Long) : FragmentPagerAdapter(fm) {

    private val tabCount = 2
    private val payload = Bundle().apply { putLong(ItemsListView.DEPT_ID, deptId) }

    override fun getItem(position: Int) =
            when (position) {
                0 -> EmployeeListFragment().apply {
                    arguments = payload
                }
                1 -> ProductListFragment().apply {
                    arguments = payload
                }
                else -> Fragment()
            }

    override fun getCount() = tabCount

    override fun getPageTitle(position: Int) =
            when (position) {
                0 -> "Employees"
                else -> "Products(TODO)"
            }
}