package com.example.srawa.mvpkotlin.ui.departmentdetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import com.example.srawa.mvpkotlin.ui.departmentdetail.employee.EmployeeListFragment
import com.example.srawa.mvpkotlin.ui.departmentdetail.employee.EmployeeListView

class ViewPagerAdapter(fm: FragmentManager, var deptId: Long) : FragmentPagerAdapter(fm) {

    private val tabCount = 2

    override fun getItem(position: Int) =
            when (position) {
                0 -> EmployeeListFragment().apply {
                    Log.d("xyz", "GOT HERE IN LIST deptID = ${this@ViewPagerAdapter.deptId}")
                    arguments = Bundle().apply { putLong(EmployeeListView.DEPT_ID, this@ViewPagerAdapter.deptId) }
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