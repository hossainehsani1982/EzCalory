package com.hossain_ehs.tracker_presentation.tracker_overview

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.hossain_ehs.core_ui.LocalSpacing
import androidx.compose.foundation.layout.*
import androidx.compose.ui.tooling.preview.Preview
import com.hossain_ehs.core.R
import com.hossain_ehs.tracker_presentation.tracker_overview.components.*
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
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(spacing.spaceSmall)
                    )
                    {
                        val foods = state.trackedFoods.filter {
                            it.mealType == meal.mealType
                        }
                        foods.forEach{
                                TrackedFoodItem(
                                    trackedFood = it,
                                    onDeleteClicked = {
                                        viewModel.onEvent(
                                            TrackerOverViewEvent.OnDeleteTrackedFoodClicked(it)
                                        )
                                    }
                                )
                        }

                        Spacer(modifier = Modifier.height(spacing.spaceMedium))

                        AddButton(
                            text = stringResource(
                                id = R.string.add_meal,
                                meal.name.asString(context)
                            ),
                            onClick = {
                                viewModel.onEvent(TrackerOverViewEvent.OnAddFoodClicked(meal))
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}