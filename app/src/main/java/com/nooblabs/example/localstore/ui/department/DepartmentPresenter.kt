package com.nooblabs.example.localstore.ui.department

import com.nooblabs.example.localstore.database.AppDatabase

interface DepartmentPresenter {

    fun loadAllDepartments(database: AppDatabase)

}