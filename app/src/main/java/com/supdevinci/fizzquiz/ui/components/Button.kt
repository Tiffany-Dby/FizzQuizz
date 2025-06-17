package com.supdevinci.fizzquiz.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BaseButton(value: String = "Click", modifier: Modifier, onClick : () -> Unit) {
    Button(onClick = onClick, modifier = modifier) {
        Text(text = value)
    }
}