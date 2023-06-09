package com.hossain_ehs.onboarding_presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.hossain_ehs.core_ui.LocalSpacing

@Composable
fun ActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    isEnable: Boolean = true,
    textStyle: TextStyle = MaterialTheme.typography.button
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = isEnable,
        shape = RoundedCornerShape(100.dp),
    ) {
        Text(
            modifier = Modifier.padding(LocalSpacing.current.spaceSmall),
            text = text,
            style = textStyle,
            color = MaterialTheme.colors.onPrimary
        )
    }
}