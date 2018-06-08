package com.nooblabs.example.localstore.ui.departmentdetail.base

import com.nooblabs.example.localstore.database.AppDatabase

interface ItemsListPresenter {

    fun loadAll(database: AppDatabase, deptId: Long)

}