package com.example.srawa.mvpkotlin.database.util.klaxon

import com.beust.klaxon.Converter
import com.beust.klaxon.JsonValue
import com.example.srawa.mvpkotlin.database.product.ProductType

object KlaxonProductTypeConverter: Converter{
    override fun canConvert(cls: Class<*>) =
        cls == ProductType::class.java

    override fun fromJson(jv: JsonValue) =
        jv.string?.let { value ->
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
        } ?: throw IllegalArgumentException("no value provided")


    override fun toJson(value: Any): String =
        """ { "type": $value } """"
}