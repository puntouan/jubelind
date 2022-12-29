package com.jubel.jubelind.plannings.application

import com.jubel.jubelind.plannings.domain.PlanningSummary
import com.jubel.jubelind.products.domain.Product
import com.jubel.jubelind.shared.domain.pagination.Page
import com.jubel.jubelind.shared.domain.pagination.PaginationParams
import com.jubel.jubelind.shared.infrastructure.ListPaginator
import java.time.LocalDateTime
import java.util.UUID
import kotlin.random.Random


class PlanningSummarySearchPaginated {

    private val rnd = Random(System.currentTimeMillis())

    private val nMocks = 80
    private val mockedData = (0..nMocks).map{
        rndPlanningSummary(it.toLong() + 1, nMocks.toLong())
    }.reversed()

    private val paginator = ListPaginator()

    fun run(paginationParams: PaginationParams): Page<PlanningSummary>{
        return paginator.getPage(mockedData, paginationParams)
    }

    private fun rndPlanningSummary(pos: Long, total: Long): PlanningSummary{

        val maxDurationPlanningInDays = 20
        val minDurationPlanningInDays = 12

        val veryFirstDay = LocalDateTime.now().minusDays(total * maxDurationPlanningInDays)

        val start = veryFirstDay.plusDays(pos * maxDurationPlanningInDays)
        val durationInDays = minDurationPlanningInDays + rnd.nextInt(9)
        val end = start.plusDays(durationInDays.toLong())

        val minCalsPerWeek = 10500
        val maxCalsPerWeek = 18000

        val minProtein = (minCalsPerWeek / 3) / Product.CALS_PER_GRAM_OF_PROTEIN
        val maxProtein = (maxCalsPerWeek / 3) / Product.CALS_PER_GRAM_OF_PROTEIN
        val protein = rnd.nextInt(maxProtein - minProtein) + minProtein

        val minFat = (minCalsPerWeek / 3) / Product.CALS_PER_GRAM_OF_FAT
        val maxFat = (maxCalsPerWeek / 3) / Product.CALS_PER_GRAM_OF_FAT
        val fat = rnd.nextInt(maxFat - minFat) + minFat

        val minCarbohydrates = (minCalsPerWeek / 3) / Product.CALS_PER_GRAM_OF_CARBOHYDRATE
        val maxCarbohydrates = (maxCalsPerWeek / 3) / Product.CALS_PER_GRAM_OF_CARBOHYDRATE
        val carbohydrates = rnd.nextInt(maxCarbohydrates - minCarbohydrates) + minCarbohydrates

        val cals = protein * Product.CALS_PER_GRAM_OF_PROTEIN +
                fat * Product.CALS_PER_GRAM_OF_FAT +
                carbohydrates + Product.CALS_PER_GRAM_OF_CARBOHYDRATE

        return PlanningSummary(
            id = UUID.randomUUID().toString(),
            start = start.toLocalDate(),
            end = end.toLocalDate(),
            calsPerWeek = cals.toDouble(),
            proteinPerWeek = protein.toDouble(),
            fatPerWeek = fat.toDouble(),
            carbohydratesPerWeek = carbohydrates.toDouble()
        )

    }
}