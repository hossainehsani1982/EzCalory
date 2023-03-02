package com.hossain_ehs.onboarding_presentation.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.hossain_ehs.core_ui.LocalSpacing
import com.hossain_ehs.core.R
import com.hossain_ehs.onboarding_presentation.components.ActionButton

@Composable
fun WelcomeScreen() {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.welcome_text),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h2
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = { /*TODO*/ },
            modifier = Modifier.align(CenterHorizontally)
             )
    }
}