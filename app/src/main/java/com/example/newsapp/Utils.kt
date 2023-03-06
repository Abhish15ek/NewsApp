package com.example.newsapp

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    @RequiresApi(Build.VERSION_CODES.N)
    fun dateFormat(oldDate: String): String? {
        val newDate: String?
        val dateFormat = SimpleDateFormat(" yyyy-MM-dd", Locale(getCountry()))

        newDate = try {
            val date: Date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH).parse(oldDate)
            dateFormat.format(date)
        } catch (e: ParseException) {
            oldDate
        }


        return newDate
    }

    private fun getCountry(): String {
        val local = Locale.getDefault()
        return local.country
    }

}