package com.nooblabs.example.localstore.ui.departmentdetail.base

import com.nooblabs.example.localstore.ui.base.MvpListView

interface ItemsListView<T> : MvpListView<T> {

    var deptId: Long

    companion object {
        const val DEPT_ID = "dept_id"
    }

}