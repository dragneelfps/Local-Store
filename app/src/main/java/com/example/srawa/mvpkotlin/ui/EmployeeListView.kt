package com.example.srawa.mvpkotlin.ui

import com.example.srawa.mvpkotlin.database.employee.Employee

interface EmployeeListView {

    fun showProgress()

    fun hideProgress()

    fun setItems(employees: List<EmployeeDetail>)

    fun displayMessage(message: String)

    fun clearItems()

    class EmployeeDetail(var employee: Employee) {
        var deptName = "NOT SET"
    }

}