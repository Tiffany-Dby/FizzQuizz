package com.supdevinci.fizzquiz.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.supdevinci.fizzquiz.models.api.Category
import com.supdevinci.fizzquiz.ui.views.QuizActivity
import com.supdevinci.fizzquiz.utils.switchActivity
import com.supdevinci.fizzquiz.viewmodels.HomeViewModel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.supdevinci.fizzquiz.ui.views.LeaderboardActivity

@Composable
fun Home(viewModel: HomeViewModel) {
    val context = LocalContext.current

    val categories by viewModel.categoriesState.collectAsState()
    val lastCategoryId by viewModel.lastCategoryId.collectAsState()
    val savedUsername by viewModel.savedUsername.collectAsState()
    var selected by remember(
        categories,
        lastCategoryId
    ) { mutableStateOf(categories?.firstOrNull { it.id == lastCategoryId }) }
    var inputUsername by rememberSaveable { mutableStateOf("") }

    val username = savedUsername ?: inputUsername

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
        ) {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.Settings, contentDescription = "Paramètres")
            }
            Text("FizzQuiz", style = MaterialTheme.typography.headlineLarge)
            IconButton(onClick = { switchActivity(context, LeaderboardActivity::class.java) }) {
                Icon(imageVector = Icons.Filled.EmojiEvents, contentDescription = "Leaderboard")
            }
        }

        Column {
            if (savedUsername.isNullOrBlank()) {
                OutlinedTextField(
                    value = inputUsername,
                    onValueChange = { inputUsername = it },
                    label = { Text("Username") },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            } else {
                Text(text = "Welcome back $savedUsername !")
            }

            Spacer(Modifier.height(12.dp))

            BaseDropdown<Category>(
                label = "Choose a category",
                options = categories ?: emptyList(),
                selectedOption = selected,
                onOptionSelect = {
                    selected = it
                    viewModel.saveLastCategory(it.id)
                    keyboardController?.hide()
                },
                toString = { it.name }
            )

            Spacer(Modifier.height(24.dp))

            BaseButton(
                onClick = {
                    viewModel.createUser(username = username)
                    selected?.let { category ->
                        switchActivity(context, QuizActivity::class.java) {
                            putExtra("categoryId", category.id)
                            putExtra("username", username)
                        }
                    }
                },
                value = "Play",
                enabled = (selected != null || username.isBlank()),
                modifier = Modifier.fillMaxWidth(),
                contentColor = Color.White,
                containerColor = Color.Gray,
            )
        }

        Spacer(Modifier.height(24.dp))
    }
}