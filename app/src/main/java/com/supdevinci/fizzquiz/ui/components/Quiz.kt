package com.supdevinci.fizzquiz.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.supdevinci.fizzquiz.viewmodels.QuizViewModel

@Composable
fun Quiz(viewModel: QuizViewModel, modifier: Modifier) {
    val question by viewModel.questionState.collectAsState()
    val choices by viewModel.choicesState.collectAsState()
    val answer by viewModel.answerState.collectAsState()
    val error by viewModel.errorState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getQuestion()
    }

    fun checkAnswer() {

    }

    if (error != null) {
        Text(text = error!!)
    } else if (question != null && answer != null && choices != null) {
        Column {
            Text(text = question!!.question)
            choices!!.forEach { answer ->
                BaseButton(
                    value = answer,
                    onClick = { checkAnswer() },
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
    } else {
        CircularProgressIndicator()
    }
}