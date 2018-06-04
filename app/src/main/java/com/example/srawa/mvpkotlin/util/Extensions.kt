package com.example.srawa.mvpkotlin.util

import java.text.SimpleDateFormat
import java.util.*

fun Date.formatDate(): String {
    val fm = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
    return fm.format(this)
}