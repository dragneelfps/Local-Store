package com.nooblabs.example.localstore.database.employee

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(employees: List<Employee>)

    @Query("SELECT * FROM employee")
    fun getAllEmployees(): List<Employee>

    @Query("SELECT * FROM employee WHERE name LIKE '%' || :name || '%'")
    fun getEmployeesByName(name: String): List<Employee>

    @Query("SELECT * FROM employee WHERE dept_id = :deptId")
    fun getEmployeesByDeptId(deptId: Long): List<Employee>

}