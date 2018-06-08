package com.example.srawa.mvpkotlin.ui.departmentdetail

import com.example.srawa.mvpkotlin.database.AppDatabase
import com.example.srawa.mvpkotlin.database.department.DepartmentRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DepartmentDetailPresenterImp(var mainView: DepartmentDetailView) : DepartmentDetailPresenter {

    override fun loadDepartmentDetail(database: AppDatabase, deptId: Long) {
        if (mainView.getRootView() != null) {
            val departmentRepo = DepartmentRepoImpl(database.departmentDao())
            departmentRepo.getDepartment(deptId)
                    .map {
                        val employeeCount = database.employeeDao().getEmployeesByDeptId(deptId).size
                        val productCount = database.productDao().getProductsByDeptId(deptId).size
                        val departmentDetail = DepartmentDetailView.DepartmentDetail(it)
                        departmentDetail.employeeCount = employeeCount
                        departmentDetail.productCount = productCount
                        departmentDetail
                    }
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        if (mainView.getRootView() != null) {
                            mainView.setDepartmentDetails(department = it)
                        }
                    }
        }
    }
}