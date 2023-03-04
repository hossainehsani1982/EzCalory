package com.hossain_ehs.tracker_data.mappers

import com.hossain_ehs.tracker_data.local.entity.TrackedFoodEntity
import com.hossain_ehs.tracker_domain.model.MealType
import com.hossain_ehs.tracker_domain.model.TrackedFood
import java.time.LocalDate

fun TrackedFoodEntity.toTrackedFood(): TrackedFood {
    return TrackedFood(
        name = name,
        carbs = carbs,
        protein = protein,
        fat = fat,
        imageUrl = imageUrl,
        mealType = MealType.fromString(type),
        amountInGr = amountInGr,
        date = LocalDate.of(year, month, dayOfMonth),
        calories = calories,
    )
}

fun TrackedFood.toTrackedFoodEntity(): TrackedFoodEntity {
    return TrackedFoodEntity(
        name = name,
        carbs = carbs,
        protein = protein,
        fat = fat,
        imageUrl = imageUrl,
        type = mealType.name,
        amountInGr = amountInGr,
        dayOfMonth = date.dayOfMonth,
        month = date.monthValue,
        year = date.year,
        calories = calories,
        id = id
    )
}