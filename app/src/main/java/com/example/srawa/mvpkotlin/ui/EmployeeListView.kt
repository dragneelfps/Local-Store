package com.example.srawa.mvpkotlin.ui

import com.example.srawa.mvpkotlin.database.employee.Employee

interface EmployeeListView {

    fun showProgress()

    fun hideProgress()

    fun setItems(employees: List<Employee>)

    fun displayMessage(message: String)

}