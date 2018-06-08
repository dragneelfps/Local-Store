package com.nooblabs.example.localstore.database.department

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation
import com.nooblabs.example.localstore.database.employee.Employee
import com.nooblabs.example.localstore.database.product.Product

class DepartmentDetail {

    @Embedded
    var department: Department? = null

    @Relation(parentColumn = "id", entityColumn = "dept_id")
    var employees: List<Employee> = ArrayList()

    @Relation(parentColumn = "id", entityColumn = "dept_id")
    var products: List<Product> = ArrayList()
}