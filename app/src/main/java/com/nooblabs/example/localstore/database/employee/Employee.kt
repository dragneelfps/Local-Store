package com.nooblabs.example.localstore.database.employee

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import com.beust.klaxon.Json
import com.nooblabs.example.localstore.database.department.Department
import com.nooblabs.example.localstore.database.util.klaxon.KlaxonDateType
import java.util.*

@Entity(tableName = "employee", foreignKeys = [(ForeignKey(entity = Department::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("dept_id"),
        onDelete = ForeignKey.CASCADE))])
data class Employee(
        @PrimaryKey(autoGenerate = true)
        var id: Long  = 0,

        @Json(name = "dept_id")
        @ColumnInfo(name = "dept_id")
        var deptID : Long,

        var name: String,

        @Json(name = "date_of_joining")
        @KlaxonDateType
        var dateOfJoining: Date
)