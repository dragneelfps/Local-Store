package com.example.srawa.mvpkotlin.ui.departmentdetail.employee

import com.example.srawa.mvpkotlin.database.AppDatabase

interface EmployeeListPresenter {

    fun loadAllEmployeesWithDeptId(database: AppDatabase, deptId: Long)

}