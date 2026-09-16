package com.example.savitsin_pr_31_pract2

import androidx.room.ColumnInfo

data class StatisticInfoTuple(
    val id: Long,
    @ColumnInfo(name = "result_name") val result: String,
    @ColumnInfo(name = "difficulty_name") val difficult: String,
    val mistakes: Long,
    val points: Long
)
