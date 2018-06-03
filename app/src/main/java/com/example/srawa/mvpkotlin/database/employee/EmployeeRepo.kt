package com.example.srawa.mvpkotlin.database.employee

import io.reactivex.Completable
import io.reactivex.Observable

interface EmployeeRepo {

    fun insertAll(employees: List<Employee>): Completable

    fun getAllEmployees(): Observable<List<Employee>>

    fun getEmployeesByName(name: String): Observable<List<Employee>>

    fun getEmployeesByDeptId(deptId: Long): Observable<List<Employee>>

}