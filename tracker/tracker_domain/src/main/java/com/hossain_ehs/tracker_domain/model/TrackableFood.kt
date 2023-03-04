package com.hossain_ehs.tracker_domain.model

data class TrackableFood(
    val name: String,
    val imageUrl : String?,
    val caloriesPer100gr : Int,
    val carbsPer100gr : Int,
    val proteinPer100gr : Int,
    val fatPer100gr : Int

)
