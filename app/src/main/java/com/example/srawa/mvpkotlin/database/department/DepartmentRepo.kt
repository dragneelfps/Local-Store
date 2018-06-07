package com.example.srawa.mvpkotlin.database.department

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface DepartmentRepo {

    fun insertAll(departments: List<Department>): Completable

    fun isDepartmentRepoEmpty(): Single<Boolean>

    fun getAllDepartments(): Observable<List<Department>>

    fun getDepartmentDetails(deptId: Long): Observable<DepartmentDetail>

    fun getDepartment(deptId: Long): Observable<Department?>

}