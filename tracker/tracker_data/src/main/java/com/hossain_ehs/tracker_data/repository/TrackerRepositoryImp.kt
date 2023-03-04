package com.hossain_ehs.tracker_data.repository

import com.hossain_ehs.tracker_data.local.TrackerDao
import com.hossain_ehs.tracker_data.mappers.toTrackableFood
import com.hossain_ehs.tracker_data.mappers.toTrackedFood
import com.hossain_ehs.tracker_data.mappers.toTrackedFoodEntity
import com.hossain_ehs.tracker_data.remote.OpenFoodApi
import com.hossain_ehs.tracker_domain.model.TrackableFood
import com.hossain_ehs.tracker_domain.model.TrackedFood
import com.hossain_ehs.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class TrackerRepositoryImp(
    private val dao: TrackerDao,
    private val api: OpenFoodApi
) : TrackerRepository{
    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return try {
            val searchDto = api.searchFood(
                query = query,
                page = page,
                pageSize = pageSize
            )
            Result.success(searchDto.products.mapNotNull {
                it.toTrackableFood()
            })
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun insertTrackedFood(trackedFood: TrackedFood) {
        dao.insertTrackedFood(trackedFood.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(trackedFood: TrackedFood) {
        dao.deleteTrackedFood(trackedFood.toTrackedFoodEntity())
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
       return dao.getFoodsBtDate(
           localDate.dayOfMonth,
           localDate.monthValue,
           localDate.year).map {trackedFoodEntityList ->
           trackedFoodEntityList.map {
               it.toTrackedFood()
           }

       }
    }
}