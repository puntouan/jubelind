package com.jubel.jubelind.shared.infrastructure.dtos

import com.google.inject.Singleton
import kotlin.math.round

@Singleton
class NumberFormatter {

    fun format(d: Double, decimals: Int = 2): Float{
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return (round(d * multiplier) / multiplier).toFloat()
    }

}