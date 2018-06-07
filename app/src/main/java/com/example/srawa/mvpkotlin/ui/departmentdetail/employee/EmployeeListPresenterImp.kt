package com.example.srawa.mvpkotlin.ui.departmentdetail.employee

import android.util.Log
import com.example.srawa.mvpkotlin.database.AppDatabase
import com.example.srawa.mvpkotlin.database.employee.EmployeeRepo
import com.example.srawa.mvpkotlin.database.employee.EmployeeRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EmployeeListPresenterImp(var mainView: EmployeeListView) : EmployeeListPresenter {

    override fun loadAllEmployeesWithDeptId(database: AppDatabase, deptId: Long) {
        Log.d("xyz", "Got employees deptId = $deptId ")

        mainView.clearItems()
        mainView.showProgress()
        val employeeRepo: EmployeeRepo = EmployeeRepoImpl(database.employeeDao())

        employeeRepo.getEmployeesByDeptId(deptId)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    it.forEach {
                        Log.d("xyz", "Got employees ")
                    }
                    mainView.setItems(items = it)
                    mainView.hideProgress()
                }
    }
}