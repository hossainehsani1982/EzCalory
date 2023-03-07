package com.hossain_ehs.tracker_domain.use_case

import com.hossain_ehs.core.domain.model.ActivityLevel
import com.hossain_ehs.core.domain.model.Gender
import com.hossain_ehs.core.domain.model.GoalType
import com.hossain_ehs.core.domain.model.UserInfo
import com.hossain_ehs.core.domain.shared_preferences.Preferences
import com.hossain_ehs.tracker_domain.model.MealType
import com.hossain_ehs.tracker_domain.model.TrackedFood
import kotlin.math.roundToInt

class CalculateMealNutrients(
    private val preferences: Preferences
) {

    operator fun invoke(trackedFoods: List<TrackedFood>): Result {

        //before groupBy, allNutrients tup is List<TrackedFood>
        //after group by it will create a Map<MealType, List<trackedFood>
        //before mapping, allNutrients  type is Map<MealType, List<TrackedFood>>
        //after mapValues, allNutrients type changed to Map<MealType, MealNutrient>
        //in shor map here take a list and sum its values and return an object

        val allNutrients = trackedFoods
            .groupBy {
                it.mealType
            }
            .mapValues { entry ->
                val type = entry.key
                val foods = entry.value
                MealNutrient(
                    carbs = foods.sumOf {
                        it.carbs
                    },
                    protein = foods.sumOf {
                        it.protein
                    },
                    fat = foods.sumOf {
                        it.fat
                    },
                    calories = foods.sumOf {
                        it.calories
                    },
                    mealType = type
                )
            }

        val totalCarbs = allNutrients.values.sumOf { it.carbs }
        val totalProteins = allNutrients.values.sumOf { it.protein }
        val totalFat = allNutrients.values.sumOf { it.fat }
        val totalCalories = allNutrients.values.sumOf { it.calories }

        val userInfo = preferences.loadUserInfo()
        val caloryGoal = dailyCaloryRequirement(userInfo)

        val carbsGoal = (caloryGoal * userInfo.carbRatio / 4f).roundToInt()
        val proteinGoal = (caloryGoal * userInfo.proteinRatio / 4f).roundToInt()
        val fatGoal = (caloryGoal * userInfo.fatRatio / 9f).roundToInt()

        return Result(
            carbsGoal = carbsGoal,
            proteinGoal = proteinGoal,
            fatGoal = fatGoal,
            caloriesGoal = caloryGoal,
            totalCarbs = totalCarbs,
            totalProteins = totalProteins,
            totalFat = totalFat,
            totalCalories = totalCalories,
            mealNutrient = allNutrients
        )
    }

    private fun bmr(userInfo: UserInfo): Int {
        return when (userInfo.gender) {
            is Gender.Male -> {
                (66.47f + 13.75f * userInfo.weight +
                        5f * userInfo.height - 6.75f * userInfo.age).roundToInt()
            }
            is Gender.Female -> {
                (665.09f + 9.56f * userInfo.weight +
                        1.84f * userInfo.height - 4.67 * userInfo.age).roundToInt()
            }
        }
    }

    private fun dailyCaloryRequirement(userInfo: UserInfo): Int {
        val activityFactor = when (userInfo.activityLevel) {
            is ActivityLevel.Low -> 1.2f
            is ActivityLevel.Medium -> 1.3f
            is ActivityLevel.High -> 1.4f
        }
        val caloryExtra = when (userInfo.goalType) {
            is GoalType.LoseWeight -> -500
            is GoalType.KeepWeight -> 0
            is GoalType.GainWeight -> 500
        }
        return (bmr(userInfo) * activityFactor + caloryExtra).roundToInt()
    }

    //will save nutrient for one meal
    data class MealNutrient(
        val carbs: Int,
        val protein: Int,
        val fat: Int,
        val calories: Int,
        val mealType: MealType
    )

    //this is what wll be returned form this use case
    data class Result(
        val carbsGoal: Int,
        val proteinGoal: Int,
        val fatGoal: Int,
        val caloriesGoal: Int,
        val totalCarbs: Int,
        val totalProteins: Int,
        val totalFat: Int,
        val totalCalories: Int,
        val mealNutrient: Map<MealType, MealNutrient>
    )

}