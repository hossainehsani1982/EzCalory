package com.hossain_ehs.tracker_presentation.tracker_overview

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.hossain_ehs.core_ui.LocalSpacing
import com.hossain_ehs.tracker_presentation.tracker_overview.components.DaySelector
import com.hossain_ehs.tracker_presentation.tracker_overview.components.ExpandableMeal
import com.hossain_ehs.tracker_presentation.tracker_overview.components.NutrientHeader

@Composable
fun TrackerOverViewScreen(
    viewModel: TrackerOverViewViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = spacing.spaceMedium)
    ) {
        item {
            NutrientHeader(state = state)
            DaySelector(
                date = state.date,
                onNextDayClicked = {
                    viewModel.onEvent(TrackerOverViewEvent.OnNextDayClicked)
                },
                onPreviousDayClicked = {
                    viewModel.onEvent(TrackerOverViewEvent.OnPreviousDayClicked)
                },
                modifier = Modifier.padding(
                    horizontal = spacing.spaceMedium
                )
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
        }
        items(state.meal) { meal ->
            ExpandableMeal(
                meal = meal,
                onToggleClicked = {
                    viewModel.onEvent(TrackerOverViewEvent.OnToggleMealClicked(meal))
                },
                contents = {

                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}