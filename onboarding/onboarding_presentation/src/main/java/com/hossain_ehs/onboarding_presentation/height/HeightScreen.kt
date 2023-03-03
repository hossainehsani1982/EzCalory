package com.hossain_ehs.onboarding_presentation.height

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.hossain_ehs.core.R
import com.hossain_ehs.core_ui.LocalSpacing
import com.hossain_ehs.onboarding_presentation.components.ActionButton
import com.hossain_ehs.onboarding_presentation.components.UnitTextField

@Composable
fun HeightScreen(
    viewModel: HeightViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(spacing.spaceMedium))
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(spacing.spaceMedium),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.whats_your_height),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h3
            )

            Spacer(modifier = Modifier.height(spacing.spaceMedium))

            UnitTextField(value = viewModel.height
                , onValueChanged = viewModel::onHeightEntered
                , unit = stringResource(id = R.string.cm)
            )
        }

        ActionButton(text = stringResource(
            id = R.string.next),
            onClick = viewModel::onNextClicked,
            modifier = Modifier.align(Alignment.BottomEnd))
    }
}