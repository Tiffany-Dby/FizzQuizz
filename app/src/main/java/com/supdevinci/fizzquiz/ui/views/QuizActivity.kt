package com.supdevinci.fizzquiz.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.supdevinci.fizzquiz.ui.components.Quiz
import com.supdevinci.fizzquiz.ui.theme.FizzQuizTheme
import com.supdevinci.fizzquiz.viewmodels.QuizViewModel

class QuizActivity: ComponentActivity() {
    private val quizViewModel : QuizViewModel = QuizViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val categoryId = intent.getIntExtra("categoryId", 0)

        enableEdgeToEdge()
        setContent {
            FizzQuizTheme {
                Scaffold { innerPadding ->
                    Surface(modifier = Modifier.fillMaxSize().padding(innerPadding).padding(20.dp)) {
                        Quiz(viewModel = quizViewModel, modifier = Modifier.padding(), categoryId)
                    }
                }
            }
        }
    }
}