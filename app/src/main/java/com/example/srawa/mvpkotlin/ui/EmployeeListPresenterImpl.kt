package com.example.srawa.mvpkotlin.ui

import com.example.srawa.mvpkotlin.database.AppDatabase
import com.example.srawa.mvpkotlin.database.employee.EmployeeRepo
import com.example.srawa.mvpkotlin.database.employee.EmployeeRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EmployeeListPresenterImpl(
        var mainView: EmployeeListView)
    : EmployeeListPresenter {


    override fun searchEmployeesByName(database: AppDatabase, name: String) {
        mainView.clearItems()
        mainView.showProgress()

        val employeeRepo: EmployeeRepo = EmployeeRepoImpl(database.employeeDao())

        employeeRepo.getEmployeesByName(name).map {
            val empDetails = ArrayList<EmployeeListView.EmployeeDetail>()
            it.forEach { emp ->
                val deptId = emp.deptID
                val empDetail = EmployeeListView.EmployeeDetail(emp)
                val depts = database.departmentDao().getDepartmentById(deptId)
                if (depts.isEmpty()) {
                    empDetail.deptName = "INVALID"
                } else {
                    empDetail.deptName = depts[0].name
                }
                empDetails.add(empDetail)
            }
            empDetails
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    mainView.setItems(employees = it)
                    mainView.hideProgress()
                }
    }
}