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
import com.supdevinci.fizzquiz.ui.components.Home
import com.supdevinci.fizzquiz.ui.theme.FizzQuizTheme
import com.supdevinci.fizzquiz.viewmodels.HomeViewModel

class HomeActivity : ComponentActivity() {
    private val viewModel: HomeViewModel = HomeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCategories()
        enableEdgeToEdge()
        setContent {
            FizzQuizTheme {
                Scaffold { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(20.dp)
                    ) {
                        Home(viewModel)
                    }
                }
            }
        }
    }
}