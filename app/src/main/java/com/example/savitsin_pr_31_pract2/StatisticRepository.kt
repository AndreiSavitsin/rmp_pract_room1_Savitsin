package com.example.savitsin_pr_31_pract2

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StatisticRepository(private val statisticDao: StatisticDao) {

    suspend fun insertNewStatisticData(statisticDbEntity: StatisticDbEntity) {
        withContext(Dispatchers.IO) {
            statisticDao.insertNewStatisticData(statisticDbEntity)
        }
    }

    suspend fun getAllStatisticData() : List<StatisticInfoTuple> {
        return withContext(Dispatchers.IO) {
            return@withContext statisticDao.getAllStatisticData()
        }
    }

    suspend fun removeStatisticDataById(id: Long) {
        withContext(Dispatchers.IO) {
            statisticDao.deleteStatisticDataById(id)
        }
    }
}