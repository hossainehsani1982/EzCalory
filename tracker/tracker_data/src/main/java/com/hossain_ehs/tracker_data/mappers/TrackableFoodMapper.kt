package com.hossain_ehs.tracker_data.mappers

import com.hossain_ehs.tracker_data.remote.dto.Product
import com.hossain_ehs.tracker_domain.model.TrackableFood
import kotlin.math.roundToInt

fun Product.toTrackableFood(): TrackableFood? {
    return TrackableFood(
        name = productName ?: return null,
        imageUrl = imageFrontThumbUrl,
        caloriesPer100gr = nutriments.energyKcal100g.roundToInt(),
        carbsPer100gr = nutriments.carbohydrates100g.roundToInt(),
        proteinPer100gr = nutriments.proteins100g.roundToInt(),
        fatPer100gr = nutriments.fat100g.roundToInt()
    )
}