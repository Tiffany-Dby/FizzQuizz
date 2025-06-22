package com.supdevinci.fizzquiz.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BaseButton(
    value: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    border: BorderStroke = BorderStroke(1.dp, Color.Black),
    cornerRadius: Dp = 2.dp,
    elevation: ButtonElevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
    keepColorsOnDisabled: Boolean = false,
    onClick: () -> Unit
) {
    val colors = if (keepColorsOnDisabled) {
        ButtonDefaults.buttonColors(
            containerColor         = containerColor,
            contentColor           = contentColor,
            disabledContainerColor = containerColor,
            disabledContentColor   = contentColor
        )
    } else {
        ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor   = contentColor
        )
    }

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(cornerRadius),
        border = border,
        elevation = elevation,
        colors = colors,
    ) {
        Text(text = value)
    }
}

