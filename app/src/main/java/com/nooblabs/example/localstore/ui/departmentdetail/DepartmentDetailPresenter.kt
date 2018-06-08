package com.nooblabs.example.localstore.ui.departmentdetail

import com.nooblabs.example.localstore.database.AppDatabase

interface DepartmentDetailPresenter {

    fun loadDepartmentDetail(database: AppDatabase, deptId: Long)

}