package com.example.srawa.mvpkotlin.ui

import android.content.Context
import com.example.srawa.mvpkotlin.database.AppDatabase
import com.example.srawa.mvpkotlin.database.employee.EmployeeRepo
import com.example.srawa.mvpkotlin.database.employee.EmployeeRepoImpl
import com.example.srawa.mvpkotlin.util.DatabaseBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EmployeeListPresenterImpl(
        var mainView: EmployeeListView)
    : EmployeeListPresenter {

    override fun searchEmployeesByName(database: AppDatabase, name: String) {
        mainView.clearItems()
        mainView.showProgress()
        val employeeRepo: EmployeeRepo = EmployeeRepoImpl(database.employeeDao())
        employeeRepo.getEmployeesByName(name).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    mainView.setItems(employees = it)
                    mainView.hideProgress()
                }
    }
}