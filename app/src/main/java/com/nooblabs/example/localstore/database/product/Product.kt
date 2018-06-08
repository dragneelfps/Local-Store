package com.nooblabs.example.localstore.database.product

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import com.beust.klaxon.Json
import com.nooblabs.example.localstore.database.department.Department
import com.nooblabs.example.localstore.database.util.klaxon.KlaxonProductType

@Entity(tableName = "product", foreignKeys = [(ForeignKey(entity = Department::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("dept_id"),
        onDelete = ForeignKey.CASCADE))])
data class Product(
        @PrimaryKey(autoGenerate = true)
        var id: Long = 0,

        @Json(name = "dept_id")
        @ColumnInfo(name = "dept_id")
        var deptID: Long,

        var name: String,

        @KlaxonProductType
        var type: ProductType = ProductType.MISC,

        var quantity: Long
)