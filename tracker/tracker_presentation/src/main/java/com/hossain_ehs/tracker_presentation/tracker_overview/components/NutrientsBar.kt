package com.hossain_ehs.tracker_presentation.tracker_overview.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import com.hossain_ehs.core_ui.CarbColor
import com.hossain_ehs.core_ui.FatColor
import com.hossain_ehs.core_ui.ProteinColor

@Composable
fun NutrientsBar(
    carbs : Int,
    protein: Int,
    fat : Int,
    calories : Int,
    caloryGoal : Int,
    modifier: Modifier = Modifier
){
    val background = MaterialTheme.colors.background
    val caloriesExceededColor = MaterialTheme.colors.error

    val carbsWidthRatio = remember {
        Animatable(0f)
    }
    val proteinWidthRatio = remember {
        Animatable(0f)
    }
    val fatWidthRatio = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = carbs){
        carbsWidthRatio.animateTo(
            targetValue = ((carbs * 4f) / caloryGoal),
        )
    }
    LaunchedEffect(key1 = protein){
        proteinWidthRatio.animateTo(
            targetValue = ((protein * 4f) / caloryGoal),
        )
    }
    LaunchedEffect(key1 = fat){
        fatWidthRatio.animateTo(
            targetValue = ((fat * 9f) / caloryGoal),
        )
    }
    Canvas(modifier = modifier){
        if (calories <= caloryGoal){
            val carbsWidth = carbsWidthRatio.value * size.width
            val proteinWidth = proteinWidthRatio.value * size.width
            val fatWith = fatWidthRatio.value * size.width
            drawRoundRect(
                color = background,
                size = size,
                cornerRadius = CornerRadius(100f)
            )
            drawRoundRect(
                color = FatColor,
                size = Size(
                    width = carbsWidth + proteinWidth + fatWith,
                    height = size.height
                ),
                cornerRadius = CornerRadius(100f)
            )
            drawRoundRect(
                color = ProteinColor,
                size = Size(
                    width = carbsWidth + proteinWidth,
                    height = size.height
                ),
                cornerRadius = CornerRadius(100f)
            )
            drawRoundRect(
                color = CarbColor,
                size = Size(
                    width = carbsWidth,
                    height = size.height
                ),
                cornerRadius = CornerRadius(100f)
            )
        }else{
            drawRoundRect(
                color = caloriesExceededColor,
                size = size,
                cornerRadius = CornerRadius(100f)
            )
        }
    }
}