package com.nooblabs.example.localstore.ui.department

import com.nooblabs.example.localstore.database.AppDatabase
import com.nooblabs.example.localstore.database.department.DepartmentRepo
import com.nooblabs.example.localstore.database.department.DepartmentRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DepartmentPresenterImp(var departmentListView: DepartmentListView) : DepartmentPresenter {

    override fun loadAllDepartments(database: AppDatabase) {
        if (departmentListView.getRootView() != null) {
            departmentListView.clearItems()
            departmentListView.showProgress()

            val departmentRepo: DepartmentRepo = DepartmentRepoImpl(database.departmentDao())
            departmentRepo.getAllDepartments().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        if (departmentListView.getRootView() != null) {
                            departmentListView.setItems(items = it)
                            departmentListView.hideProgress()
                        }
                    }
        }
    }
}