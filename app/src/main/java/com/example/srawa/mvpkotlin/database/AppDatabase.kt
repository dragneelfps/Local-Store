package com.example.srawa.mvpkotlin.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.example.srawa.mvpkotlin.database.department.Department
import com.example.srawa.mvpkotlin.database.department.DepartmentDao
import com.example.srawa.mvpkotlin.database.employee.Employee
import com.example.srawa.mvpkotlin.database.employee.EmployeeDao
import com.example.srawa.mvpkotlin.database.product.Product
import com.example.srawa.mvpkotlin.database.product.ProductDao
import com.example.srawa.mvpkotlin.database.util.converters.RoomConverters

@Database(entities = [(Department::class), (Employee::class), (Product::class)], version = 1)
@TypeConverters(RoomConverters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun departmentDao(): DepartmentDao

    abstract fun employeeDao(): EmployeeDao

    abstract fun productDao(): ProductDao

}