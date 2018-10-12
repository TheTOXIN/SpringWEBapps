package com.toxin.springotlin.entity

import java.text.SimpleDateFormat
import java.util.*

data class Score(
        val time: String,
        val points: Int
) {
    constructor(points: Int) : this(dateFormat.format(Date()), points)

    companion object {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    }

}