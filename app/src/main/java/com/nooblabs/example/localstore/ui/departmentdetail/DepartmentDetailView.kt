package com.nooblabs.example.localstore.ui.departmentdetail

import com.nooblabs.example.localstore.database.department.Department
import com.nooblabs.example.localstore.ui.base.BaseMvpView

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