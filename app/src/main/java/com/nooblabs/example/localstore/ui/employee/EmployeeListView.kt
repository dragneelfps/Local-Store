package com.nooblabs.example.localstore.ui.employee

import com.nooblabs.example.localstore.database.employee.Employee
import com.nooblabs.example.localstore.ui.base.MvpListView

interface EmployeeListView : MvpListView<EmployeeListView.EmployeeDetail> {

    class EmployeeDetail(var employee: Employee) {
        var deptName = "NOT SET"
    }

}