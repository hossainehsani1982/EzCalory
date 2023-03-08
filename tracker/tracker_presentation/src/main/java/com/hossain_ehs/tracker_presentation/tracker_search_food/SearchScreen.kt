package com.hossain_ehs.tracker_presentation.tracker_search_food

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.hossain_ehs.core_ui.LocalSpacing
import com.hossain_ehs.core.R
import com.hossain_ehs.tracker_domain.model.MealType
import com.hossain_ehs.tracker_presentation.tracker_search_food.components.SearchTextField
import com.hossain_ehs.tracker_presentation.tracker_search_food.components.TrackableFoodItem
import java.time.LocalDate

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    mealName: String,
    dayOfMonth: Int,
    month: Int,
    year: Int
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium)
    )
    {
        Text(
            text = stringResource(id = R.string.add_meal, mealName),
            style = MaterialTheme.typography.h2
        )

        Spacer(modifier = Modifier.height(spacing.spaceMedium))

        SearchTextField(
            text = state.searchQuery,
            onValueChanged = {
                viewModel.onEvent(SearchEvents.OnQueryChanged(it))
            },
            shouldShowHint = state.isHintVisible,
            onSearchClicked = {
                keyboardController?.hide()
                viewModel.onEvent(SearchEvents.OnSearch)
            },
            onFocusChanged = {
                viewModel.onEvent(SearchEvents.OnSearchFocusChanged(it.isFocused))
            }
        )

        Spacer(modifier = Modifier.height(spacing.spaceMedium))

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.trackableFoodUiState) { food ->
                TrackableFoodItem(
                    trackableFoodUiState = food,
                    onTrackableFoodItemClicked = {
                        viewModel.onEvent(
                            SearchEvents.OnToggleTrackableFood(
                                trackableFood = food.trackableFood
                            )
                        )
                    },
                    onAmountChange = {
                        viewModel.onEvent(
                            SearchEvents.OnAmountForTrackableFoodChanged(
                                trackableFood = food.trackableFood,
                                amount = it
                            )
                        )
                    },
                    onTrackClicked = {
                        keyboardController?.hide()
                        viewModel.onEvent(
                            SearchEvents.OnTrackFoodClicked(
                                trackableFood = food.trackableFood,
                                mealType = MealType.fromString(mealName),
                                date = LocalDate.of(year, month, dayOfMonth)
                            )
                        )
                    },
                    modifier = Modifier.fillMaxHeight()
                )
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        when {
            state.isSearching -> CircularProgressIndicator()
            state.trackableFoodUiState.isEmpty() ->{
                Text(
                    text = stringResource(id = R.string.no_results),
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}