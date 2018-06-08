package com.nooblabs.example.localstore.ui.employee

import com.nooblabs.example.localstore.database.AppDatabase

interface EmployeeListPresenter {

    fun searchEmployeesByName(database: AppDatabase, name: String)

}