package com.example.srawa.mvpkotlin.database.employee

import io.reactivex.Completable
import io.reactivex.Observable

class EmployeeRepoImpl(var employeeDao: EmployeeDao): EmployeeRepo {

    override fun insertAll(employees: List<Employee>): Completable =
        Completable.create { emitter ->
            employeeDao.insertAll(employees)
            emitter.onComplete()
        }

    override fun getAllEmployees(): Observable<List<Employee>> =
        Observable.fromCallable {
            employeeDao.getAllEmployees()
        }

    override fun getEmployeesByName(name: String): Observable<List<Employee>> =
        Observable.fromCallable {
            Thread.sleep(5000)
            employeeDao.getEmployeesByName(name)
        }

    override fun getEmployeesByDeptId(deptId: Long): Observable<List<Employee>> =
        Observable.fromCallable {
            employeeDao.getEmployeesByDeptId(deptId)
        }
}