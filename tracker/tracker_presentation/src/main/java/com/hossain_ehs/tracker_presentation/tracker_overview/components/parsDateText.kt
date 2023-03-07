package com.hossain_ehs.tracker_presentation.tracker_overview.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.hossain_ehs.core.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun parsDateText(date: LocalDate) :  String{
    val toDay = LocalDate.now()
    return when(date){
        toDay -> stringResource(id = R.string.today)
        toDay.plusDays(1) -> stringResource(id = R.string.tomorrow)
        toDay.minusDays(1) -> stringResource(id = R.string.yesterday)
        else -> DateTimeFormatter.ofPattern("dd LLLL").format(date)
    }
}