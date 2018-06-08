package com.nooblabs.example.localstore.database.util.converters

import android.arch.persistence.room.TypeConverter
import com.nooblabs.example.localstore.database.product.ProductType
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class RoomConverters {
    val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)

    @TypeConverter
    fun dateFromString(value: String) =
        dateFormat.parse(value)

    @TypeConverter
    fun dateToString(date: Date) =
        dateFormat.format(date)

    @TypeConverter
    fun productTypeFromString(value: String) =
            when(value){
                "DIARY" -> ProductType.DIARY
                "ELECTRONIC" -> ProductType.ELECTRONIC
                "CLOTHES" -> ProductType.CLOTHES
                "FOOD" -> ProductType.FOOD
                "UTENSIL" -> ProductType.UTENSIL
                "GAMING" -> ProductType.GAMING
                "MISC" -> ProductType.MISC
                else -> throw IllegalArgumentException("Invalid argument provided")
            }

    @TypeConverter
    fun productTypeToString(productType: ProductType) =
            productType.toString()

}