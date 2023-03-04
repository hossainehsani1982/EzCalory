package com.hossain_ehs.tracker_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TrackedFoodEntity(
    val name : String,
    val carbs : Int,
    val protein : Int,
    val fat : Int,
    val imageUrl : String?,
    val type : String,
    val amountInGr : Int,
    val dayOfMonth : Int,
    val moth : Int,
    val year : Int,
    val calories : Int,
    @PrimaryKey(autoGenerate = true) val id : Int? = null
)