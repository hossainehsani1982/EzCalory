package com.hossain_ehs.onboarding_presentation.age

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.hossain_ehs.core_ui.LocalSpacing
import com.hossain_ehs.onboarding_presentation.components.ActionButton
import com.hossain_ehs.onboarding_presentation.components.UnitTextField

@Composable
fun AgeScreen(
    viewModel: AgeViewModel = hiltViewModel()
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
               text = stringResource(id = com.hossain_ehs.core.R.string.whats_your_age),
               textAlign = TextAlign.Center,
               style = MaterialTheme.typography.h3
           )

           Spacer(modifier = Modifier.height(spacing.spaceMedium))

           UnitTextField(value = viewModel.age
               , onValueChanged = viewModel::onAgeEntered
               , unit = stringResource(id = com.hossain_ehs.core.R.string.years))
       }

       ActionButton(text = stringResource(
           id = com.hossain_ehs.core.R.string.next),
           onClick = viewModel::onNextClicked,
           modifier = Modifier.align(Alignment.BottomEnd))
   }
}