package com.hossain_ehs.core.data.shared_preferences

import android.content.SharedPreferences
import com.hossain_ehs.core.domain.model.ActivityLevel
import com.hossain_ehs.core.domain.model.Gender
import com.hossain_ehs.core.domain.model.GoalType
import com.hossain_ehs.core.domain.model.UserInfo
import com.hossain_ehs.core.domain.shared_preferences.Preferences

class DefaultPreferences(
    private val sharedPreferences: SharedPreferences
) : Preferences {
    override fun saveGender(gender: Gender) {
        sharedPreferences.edit()
            .putString(Preferences.KEY_GENDER, gender.name)
            .apply()
    }

    override fun saveAge(age: Int) {
        sharedPreferences.edit()
            .putInt(Preferences.KEY_AGE, age)
            .apply()
    }

    override fun saveWeight(weight: Float) {
        sharedPreferences.edit()
            .putFloat(Preferences.KEY_WEIGHT, weight)
            .apply()
    }

    override fun saveHeight(height: Int) {
        sharedPreferences.edit()
            .putInt(Preferences.KEY_HEIGHT, height)
            .apply()
    }

    override fun saveActivityLevel(activityLevel: ActivityLevel) {
        sharedPreferences.edit()
            .putString(Preferences.KEY_ACTIVITY_LEVEL, activityLevel.name)
            .apply()
    }

    override fun saveGoalType(goalType: GoalType) {
        sharedPreferences.edit()
            .putString(Preferences.KEY_ACTIVITY_LEVEL, goalType.name)
            .apply()
    }

    override fun saveCarbRatio(ratio: Float) {
        sharedPreferences.edit()
            .putFloat(Preferences.KEY_CARB_RATIO, ratio)
            .apply()
    }

    override fun saveProteinRatio(ratio: Float) {
        sharedPreferences.edit()
            .putFloat(Preferences.KEY_PROTEIN_RATIO, ratio)
            .apply()
    }

    override fun saveFatRatio(ratio: Float) {
        sharedPreferences.edit()
            .putFloat(Preferences.KEY_FAT_RATIO, ratio)
            .apply()
    }

    override fun loadUserInfo(): UserInfo {
        val gender = sharedPreferences.getString(Preferences.KEY_GENDER, null)
        val activityLevel = sharedPreferences.getString(Preferences.KEY_ACTIVITY_LEVEL, null)
        val goalType = sharedPreferences.getString(Preferences.KEY_GOAL_TYPE, null)
        return UserInfo(
            gender = Gender.fromString(gender ?: "male"),
            age = sharedPreferences.getInt(Preferences.KEY_AGE, -1),
            weight = sharedPreferences.getFloat(Preferences.KEY_WEIGHT, -1f),
            height = sharedPreferences.getInt(Preferences.KEY_HEIGHT, -1),
            activityLevel = ActivityLevel.fromString(activityLevel ?: "medium"),
            goalType = GoalType.fromString(goalType ?: "keepWeight"),
            carRatio = sharedPreferences.getFloat(Preferences.KEY_CARB_RATIO, -1f),
            proteinRatio = sharedPreferences.getFloat(Preferences.KEY_PROTEIN_RATIO, -1f),
            fatRatio = sharedPreferences.getFloat(Preferences.KEY_FAT_RATIO, -1f)
        )
    }
}