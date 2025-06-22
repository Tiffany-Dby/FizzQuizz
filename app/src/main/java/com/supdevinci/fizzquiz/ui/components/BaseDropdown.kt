package com.supdevinci.fizzquiz.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> BaseDropdown(
    label: String,
    options: List<T>,
    selectedOption: T?,
    onOptionSelect: (T) -> Unit,
    modifier: Modifier = Modifier,
    toString: (T) -> String = { it.toString() }
) {
    var expanded by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf("") }

    val filtered = remember(query, options) {
        if (query.isBlank()) options
        else options.filter { toString(it).contains(query, ignoreCase = true) }
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = modifier.fillMaxWidth()
    ) {
        TextField(
            value = if (expanded) query else selectedOption?.let(toString) ?: "",
            onValueChange = {
                query = it
                expanded = true
            },
            label = { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryEditable, true)
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            filtered.forEach { option ->
                DropdownMenuItem(text = { Text(toString(option)) }, onClick = {
                    onOptionSelect(option)
                    query = toString(option)
                    expanded = false
                })
            }
        }
    }
}
