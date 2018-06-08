package com.example.srawa.mvpkotlin.ui.departmentdetail.base

import com.example.srawa.mvpkotlin.database.AppDatabase

interface ItemsListPresenter {

    fun loadAll(database: AppDatabase, deptId: Long)

}