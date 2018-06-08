package com.example.srawa.mvpkotlin.ui.departmentdetail

import com.example.srawa.mvpkotlin.database.department.Department
import com.example.srawa.mvpkotlin.ui.base.BaseMvpView

interface DepartmentDetailView : BaseMvpView {

    var deptId: Long

    companion object {
        val DEPT_ID = "dept_id"
    }

    fun setDepartmentDetails(department: DepartmentDetail)

    class DepartmentDetail(var department: Department) {
        var employeeCount = -1
        var productCount = -1
    }

}