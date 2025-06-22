package com.supdevinci.fizzquiz.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun BaseDialog(
    title: String? = null,
    text: String? = null,
    onDismiss: () -> Unit,
    confirmLabel: String,
    onConfirm: () -> Unit,
    dismissLabel: String? = null,
    onDismissAction: (() -> Unit)? = null
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { if (title != null) Text(title, style = MaterialTheme.typography.titleLarge) },
        text = { if (text != null) Text(text, style = MaterialTheme.typography.bodyMedium) },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm()
                    onDismiss()
                },
                colors = ButtonDefaults.textButtonColors()
            ) {
                Text(confirmLabel.uppercase(), style = MaterialTheme.typography.labelLarge)
            }
        },
        dismissButton = {
            if (dismissLabel != null && onDismissAction != null) {
                TextButton(
                    onClick = {
                        onDismissAction()
                        onDismiss()
                    },
                    colors = ButtonDefaults.textButtonColors()
                ) {
                    Text(dismissLabel.uppercase(), style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    )
}