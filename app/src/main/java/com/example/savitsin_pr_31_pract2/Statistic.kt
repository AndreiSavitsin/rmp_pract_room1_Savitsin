package com.example.savitsin_pr_31_pract2

data class Statistic(
    val resultId: Long,
    val difficultId: Long,
    val mistakes: Long,
    val points: Long
) {
    fun toStatisticDbEntity(): StatisticDbEntity = StatisticDbEntity(
        id = 0,
        resultId = resultId,
        difficultId = difficultId,
        mistakes = mistakes,
        points = points
    )
}
