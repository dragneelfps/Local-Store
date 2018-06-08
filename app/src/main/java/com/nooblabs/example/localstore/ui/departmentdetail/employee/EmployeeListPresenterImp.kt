package com.nooblabs.example.localstore.ui.departmentdetail.employee

import com.nooblabs.example.localstore.database.AppDatabase
import com.nooblabs.example.localstore.database.employee.EmployeeRepo
import com.nooblabs.example.localstore.database.employee.EmployeeRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EmployeeListPresenterImp(var mainView: EmployeeListView) : EmployeeListPresenter {

    override fun loadAll(database: AppDatabase, deptId: Long) {
        if (mainView.getRootView() != null) {
            mainView.clearItems()
            mainView.showProgress()
            val employeeRepo: EmployeeRepo = EmployeeRepoImpl(database.employeeDao())

            employeeRepo.getEmployeesByDeptId(deptId)
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        if (mainView.getRootView() != null) {
                            mainView.setItems(items = it)
                            mainView.hideProgress()
                        }
                    }
        }
    }
}