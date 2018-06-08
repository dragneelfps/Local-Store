package com.nooblabs.example.localstore.database.department

import android.util.Log
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class DepartmentRepoImpl(private val departmentDao: DepartmentDao): DepartmentRepo{

    override fun insertAll(departments: List<Department>): Completable =
        Completable.create { emitter ->
            departmentDao.insertAll(departments)
            emitter.onComplete()
        }


    override fun isDepartmentRepoEmpty(): Single<Boolean> =
        Single.create<Boolean> { emitter ->
            val noOfDepartments = departmentDao.getAllDepartments().size
            emitter.onSuccess(noOfDepartments == 0)
        }

    override fun getAllDepartments(): Observable<List<Department>> =
            Observable.fromCallable {
                departmentDao.getAllDepartments()
        }

    override fun getDepartmentDetails(deptId: Long): Observable<DepartmentDetail> =
        Observable.create { emitter ->
            val departmentDetails = departmentDao.getDepartmentWithDetailsById(deptId)
            departmentDetails.forEach {
                Log.d("xyz","Department: " + it.department?.name)
                Log.d("xyz","Employees")
                it.employees.forEach {
                    Log.d("xyz",it.toString())
                }
                Log.d("xyz","Products")
                it.products.forEach {
                    Log.d("xyz",it.toString())
                }
            }
            //Not emitting anything right now
            emitter.onComplete()
        }

    override fun getDepartment(deptId: Long): Observable<Department?> =
            Observable.create { emitter ->
                val depts = departmentDao.getDepartmentById(deptId)
                if (depts.isNotEmpty()) emitter.onNext(depts[0])
                emitter.onComplete()
            }
}