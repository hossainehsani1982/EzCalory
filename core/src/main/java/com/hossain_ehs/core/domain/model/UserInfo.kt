package com.hossain_ehs.core.domain.model

data class UserInfo(
    val gender: Gender,
    val age: Int,
    val weight: Float,
    val height: Int,
    val activityLevel: ActivityLevel,
    val goalType: GoalType,
    val carRatio: Float,
    val proteinRatio: Float,
    val fatRatio: Float
)
