package com.hossain_ehs.tracker_presentation.tracker_search_food

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.hossain_ehs.core_ui.LocalSpacing
import com.hossain_ehs.core.R
import com.hossain_ehs.tracker_presentation.tracker_search_food.components.SearchTextField

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
            onSearchClicked = {
                viewModel.onEvent(SearchEvents.OnSearch)
            },
            onFocusChanged = {
                viewModel.onEvent(SearchEvents.OnSearchFocusChanged(it.isFocused))
            }
        )
    }
}