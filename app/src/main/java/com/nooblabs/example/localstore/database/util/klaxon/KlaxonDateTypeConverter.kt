package com.nooblabs.example.localstore.database.util.klaxon

import com.beust.klaxon.Converter
import com.beust.klaxon.JsonValue
import java.text.SimpleDateFormat
import java.util.*

object KlaxonDateTypeConverter: Converter {
    override fun canConvert(cls: Class<*>) =
        cls == Date::class.java

    override fun fromJson(jv: JsonValue) =
        jv.string?.let { value ->
            val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
            dateFormat.parse(value)
        } ?: throw IllegalArgumentException("No argument provided")

    override fun toJson(value: Any): String =
        ""
}