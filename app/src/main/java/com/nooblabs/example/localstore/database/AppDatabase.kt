package com.nooblabs.example.localstore.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.nooblabs.example.localstore.database.department.Department
import com.nooblabs.example.localstore.database.department.DepartmentDao
import com.nooblabs.example.localstore.database.employee.Employee
import com.nooblabs.example.localstore.database.employee.EmployeeDao
import com.nooblabs.example.localstore.database.product.Product
import com.nooblabs.example.localstore.database.product.ProductDao
import com.nooblabs.example.localstore.database.util.converters.RoomConverters

@Database(entities = [(Department::class), (Employee::class), (Product::class)], version = 1, exportSchema = false)
@TypeConverters(RoomConverters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun departmentDao(): DepartmentDao

    abstract fun employeeDao(): EmployeeDao

    abstract fun productDao(): ProductDao

}