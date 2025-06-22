package com.supdevinci.fizzquiz.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun <T> BaseTable(
    columns: List<Pair<String, (T) -> String>>,
    items: List<T>,
    modifier: Modifier,
    headerBackground: Color = Color.LightGray,
    headerColor: Color = Color.Black,
    cellPadding: Dp = 8.dp
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(headerBackground)
                .padding(vertical = cellPadding / 2), verticalAlignment = Alignment.CenterVertically
        ) {
            columns.forEach { (title, _) ->
                Text(
                    text = title,
                    modifier = Modifier
                        .weight(1f / columns.size)
                        .padding(horizontal = cellPadding),
                    style = MaterialTheme.typography.labelLarge,
                    color = headerColor
                )
            }
        }

        HorizontalDivider(thickness = 1.dp, color = Color.Gray)

        items.forEachIndexed { index, item ->
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = cellPadding / 2), verticalAlignment = Alignment.CenterVertically) {
                columns.forEach { (_, extractor) ->
                    Text(text = extractor(item), modifier = Modifier.weight(1f / columns.size).padding(horizontal = cellPadding), style = MaterialTheme.typography.bodyLarge)
                }
            }

            HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)
        }
    }
}