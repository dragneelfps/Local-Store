package com.example.srawa.mvpkotlin.ui.departmentdetail.employee

import com.example.srawa.mvpkotlin.database.employee.Employee
import com.example.srawa.mvpkotlin.ui.base.MvpListView

interface EmployeeListView : MvpListView<Employee> {

    var deptId: Long

    companion object {
        val DEPT_ID = "dept_id"
    }

}