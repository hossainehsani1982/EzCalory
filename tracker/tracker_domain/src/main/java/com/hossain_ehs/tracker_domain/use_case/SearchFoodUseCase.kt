package com.hossain_ehs.tracker_domain.use_case

import com.hossain_ehs.tracker_domain.model.TrackableFood
import com.hossain_ehs.tracker_domain.repository.TrackerRepository

class SearchFoodUseCase(
    private val repository: TrackerRepository
) {
    suspend operator fun invoke(
        query : String,
        page : Int,
        pageSize : Int
    ): Result<List<TrackableFood>>{
        if (query.isBlank()){
            return Result.success(emptyList())
        }
        return repository.searchFood(
            query= query.trim(),
            page = page,
            pageSize = pageSize
        )
    }
}