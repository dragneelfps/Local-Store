package com.example.srawa.mvpkotlin.ui.department

import com.example.srawa.mvpkotlin.database.AppDatabase

interface DepartmentPresenter {

    fun loadAllDepartments(database: AppDatabase)

}