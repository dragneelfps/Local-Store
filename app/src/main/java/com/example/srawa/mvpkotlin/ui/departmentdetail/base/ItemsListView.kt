package com.example.srawa.mvpkotlin.ui.departmentdetail.base

import com.example.srawa.mvpkotlin.ui.base.MvpListView

interface ItemsListView<T> : MvpListView<T> {

    var deptId: Long

    companion object {
        const val DEPT_ID = "dept_id"
    }

}