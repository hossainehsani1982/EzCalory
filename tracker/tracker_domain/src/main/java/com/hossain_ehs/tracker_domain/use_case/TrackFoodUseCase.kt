package com.hossain_ehs.tracker_domain.use_case

import com.hossain_ehs.tracker_domain.model.MealType
import com.hossain_ehs.tracker_domain.model.TrackableFood
import com.hossain_ehs.tracker_domain.model.TrackedFood
import com.hossain_ehs.tracker_domain.repository.TrackerRepository
import java.time.LocalDate
import kotlin.math.roundToInt

class TrackFoodUseCase(
    private val repository: TrackerRepository
) {
    suspend operator fun invoke(
        food: TrackableFood,
        amount: Int,
        mealType: MealType,
        date: LocalDate
    ) {
        repository.insertTrackedFood(
            TrackedFood(
                name = food.name,
                carbs = ((food.carbsPer100gr / 100f) * amount).roundToInt(),
                protein = ((food.proteinPer100gr / 100f) * amount).roundToInt(),
                fat = ((food.fatPer100gr / 100f) * amount).roundToInt(),
                imageUrl = food.imageUrl,
                mealType = mealType,
                amountInGr =amount,
                date = date,
                calories = food.caloriesPer100gr
            )
        )
    }
}