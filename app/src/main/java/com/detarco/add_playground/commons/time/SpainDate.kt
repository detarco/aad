package com.detarco.add_playground.commons.time

import java.text.SimpleDateFormat
import java.util.*

class SpainDate constructor(private val date: Date) :SpainDateString {

    private val localeSpanish = Locale("es","ES")
    private val dateFormat = SimpleDateFormat("dd-mm-yyyy", localeSpanish)

    override fun dateToString(obj: Date): String = dateFormat.format(date)

}