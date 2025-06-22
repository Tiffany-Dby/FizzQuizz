package com.supdevinci.fizzquiz.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.supdevinci.fizzquiz.ui.views.HomeActivity
import com.supdevinci.fizzquiz.ui.views.LeaderboardActivity
import com.supdevinci.fizzquiz.utils.switchActivity
import com.supdevinci.fizzquiz.utils.toUtf8
import com.supdevinci.fizzquiz.viewmodels.QuizViewModel

@Composable
fun Quiz(viewModel: QuizViewModel, modifier: Modifier, categoryId: Int, username: String) {
    val question by viewModel.questionState.collectAsState()
    val choices by viewModel.choicesState.collectAsState()
    val correct by viewModel.answerState.collectAsState()
    val error by viewModel.errorState.collectAsState()
    val isLoading by viewModel.isLoadingState.collectAsState()
    val canFetch by viewModel.canFetchState.collectAsState()

    var questionCount by remember { mutableIntStateOf(1) }
    var selected by remember { mutableStateOf<String?>(null) }
    var score by remember { mutableIntStateOf(0) }
    var isGameOver by remember { mutableStateOf<Boolean>(false) }

    val context = LocalContext.current

    LaunchedEffect(questionCount) {
        viewModel.getQuestionByCategory(categoryId)
        selected = null
    }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Text("FizzQuiz", style = MaterialTheme.typography.headlineMedium)
            Text(text = "Score: $score")
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isGameOver) {
                BaseDialog(
                    title = "Game Over",
                    text = "Your final score : $score",
                    onDismiss = { },
                    confirmLabel = "Restart",
                    onConfirm = {
                        score = 0
                        switchActivity(context, HomeActivity::class.java)
                        isGameOver = false
                    },
                    dismissLabel = "Leaderboard",
                    onDismissAction = {
                        switchActivity(context, LeaderboardActivity::class.java)
                    }
                )
            }

            if (isLoading) {
                CircularProgressIndicator()
            } else {
                if (error != null) {
                    Text(text = error!!)
                } else if (question != null && correct != null && choices != null) {
                    Spacer(Modifier.height(24.dp))

                    Text(text = "Question #$questionCount :")
                    Text(text = question!!.question.toUtf8(), modifier.padding(bottom = 12.dp))
                    choices!!.forEach { choice ->
                        val isCorrect = choice == correct
                        val isSelected = choice == selected

                        val alpha = when {
                            selected == null -> 1f
                            isCorrect || isSelected -> 1f
                            else -> 0.5f
                        }

                        BaseButton(
                            value = choice.toUtf8(),
                            onClick = {
                                if (selected == null) {
                                    selected = choice
                                    viewModel.checkAnswer(choice)

                                    if (selected == correct) score++
                                    else {
                                        viewModel.saveScore(
                                            username = username,
                                            score = score
                                        )
                                        isGameOver = true
                                    }
                                }
                            },
                            enabled = selected == null,
                            keepColorsOnDisabled = true,
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                                .graphicsLayer { this.alpha = alpha },
                            containerColor = if (selected == null) Color.White
                            else if (choice == correct) Color(0xFF4CAF50)
                            else Color(0xFFF44336),
                            contentColor = Color.Black,
                            border = BorderStroke(3.dp, Color.Black),
                            cornerRadius = 4.dp,
                        )
                    }

                    if (selected != null) {
                        Spacer(Modifier.height(24.dp))
                        BaseButton(
                            value = "Next",
                            onClick = {
                                questionCount++
                            },
                            containerColor = Color(0xFFFFFFFF),
                            contentColor = Color.Black,
                            border = BorderStroke(3.dp, Color.Black),
                            cornerRadius = 4.dp,
                            enabled = canFetch || !isGameOver
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))
    }
}