package com.example.srawa.mvpkotlin.ui.employee

import com.example.srawa.mvpkotlin.database.employee.Employee
import com.example.srawa.mvpkotlin.ui.base.MvpView

interface EmployeeListView : MvpView<EmployeeListView.EmployeeDetail> {

    class EmployeeDetail(var employee: Employee) {
        var deptName = "NOT SET"
    }

}