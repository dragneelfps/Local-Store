package com.nooblabs.example.localstore.database.department

import android.arch.persistence.room.*

@Dao
interface DepartmentDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(departments: List<Department>)

//    @Query("SELECT * FROM department WHERE floor = :floor")
//    fun loadDepartmentsByFloor(floor: Int): List<Department>

    @Query("SELECT * FROM department")
    fun getAllDepartments(): List<Department>

    @Query("SELECT * FROM department WHERE id = :id")
    fun getDepartmentById(id: Long): List<Department>

    @Transaction
    @Query("SELECT * FROM department WHERE id = :id")
    fun getDepartmentWithDetailsById(id: Long): List<DepartmentDetail>

}