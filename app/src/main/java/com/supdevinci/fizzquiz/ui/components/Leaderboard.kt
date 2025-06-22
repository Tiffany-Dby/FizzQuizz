package com.supdevinci.fizzquiz.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.supdevinci.fizzquiz.viewmodels.LeaderboardViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.supdevinci.fizzquiz.models.database.LeaderboardEntity

@Composable
fun Leaderboard(viewModel: LeaderboardViewModel) {
    val leaderboard by viewModel.leaderboard.collectAsState(initial = emptyList())

    Column {
        Text(
            text = "Leaderboard",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(vertical = 24.dp)
        )

        BaseTable(
            columns = listOf(
                "Rank" to { row: LeaderboardEntity -> (leaderboard.indexOf(row) + 1).toString() },
                "Player" to { it.username },
                "Score" to { it.score.toString() },
            ),
            items = leaderboard,
            modifier = Modifier.fillMaxWidth()
        )
    }
}