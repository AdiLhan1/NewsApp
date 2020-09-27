package com.example.newsapp.utils

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    private const val CURRENT_DATETIME_PATTERN = "dd MMMM yyyy, HH:mm"
    private const val CURRENT_TIME_PATTERN = "dd MMMM yyyy, HH:mm"
    private var vibrantLightColorList = arrayOf(
        ColorDrawable(Color.parseColor("#ffeead")),
        ColorDrawable(Color.parseColor("#93cfb3")),
        ColorDrawable(Color.parseColor("#fd7a7a")),
        ColorDrawable(Color.parseColor("#faca5f")),
        ColorDrawable(Color.parseColor("#1ba798")),
        ColorDrawable(Color.parseColor("#6aa9ae")),
        ColorDrawable(Color.parseColor("#ffbf27")),
        ColorDrawable(Color.parseColor("#d93947"))
    )

    val randomDrawbleColor: ColorDrawable
        get() {
            val idx = Random().nextInt(vibrantLightColorList.size)
            return vibrantLightColorList[idx]
        }

    fun dateToTimeFormat(oldstringDate: String?): String? {
        val p = PrettyTime(Locale(country))
        var isTime: String? = null
        try {
            val sdf = SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss'Z'",
                Locale.ENGLISH
            )
            val date = sdf.parse(oldstringDate)
            isTime = p.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return isTime
    }

    fun dateFormat(oldStringDate: String): String {
        val dateFormat = SimpleDateFormat(
            CURRENT_TIME_PATTERN,
            Locale(country)
        )
        try {
            @SuppressLint("SimpleDateFormat")
            val date =
                SimpleDateFormat(CURRENT_DATETIME_PATTERN).parse(oldStringDate)
            dateFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return oldStringDate
    }

    private val country: String
        get() {
            val locale = Locale.getDefault()
            val country = locale.country
            return country.toLowerCase(Locale.ROOT)
        }
}
