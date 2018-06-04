package com.example.srawa.mvpkotlin.ui

import com.example.srawa.mvpkotlin.database.AppDatabase

interface EmployeeListPresenter {

    fun searchEmployeesByName(database: AppDatabase, name: String)

}