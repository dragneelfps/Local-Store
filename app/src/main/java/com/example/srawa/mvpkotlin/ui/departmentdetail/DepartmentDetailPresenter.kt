package com.example.srawa.mvpkotlin.ui.departmentdetail

import com.example.srawa.mvpkotlin.database.AppDatabase

interface DepartmentDetailPresenter {

    fun loadDepartmentDetail(database: AppDatabase, deptId: Long)

}