package com.example.srawa.mvpkotlin.database.department

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.beust.klaxon.Json

@Entity(tableName = "department")
data class Department(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Long = 0,

        var name: String,

        var floor: Int
)