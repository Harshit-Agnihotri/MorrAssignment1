package com.example.morrassignment

import java.util.*

class Expire() {
    val calendar by lazy { Calendar.getInstance() }

    private fun currentYear():String{
        var string: String = ""
        val numbers = calendar.get(Calendar.YEAR).toString().map{ it.toString().toInt() }.toIntArray()
        for (i in numbers.indices){
            if (i > 1){
                string += numbers[i].toString()
            }
        }
        return string
    }

    private fun currentMonth():String = (calendar.get(Calendar.MONTH) + 1).toString()

    fun expiryDate(year: String, month: String): Boolean = currentYear().toInt() <= year.toInt() && currentMonth().toInt() <= month.toInt()
}