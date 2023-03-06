package com.hossain_ehs.tracker_data.local

import androidx.room.*
import com.hossain_ehs.tracker_data.local.entity.TrackedFoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrackedFood(trackedFoodEntity: TrackedFoodEntity)

    @Delete
    suspend fun deleteTrackedFood(trackedFoodEntity: TrackedFoodEntity)

    @Query("SELECT * FROM trackedfoodentity WHERE dayOfMonth = :day AND month = :month AND year= :year")

    fun getFoodsBtDate(day: Int, month: Int, year: Int): Flow<List<TrackedFoodEntity>>
}