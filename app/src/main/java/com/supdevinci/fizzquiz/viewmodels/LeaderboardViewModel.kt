package com.supdevinci.fizzquiz.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.supdevinci.fizzquiz.data.database.QuizDatabase
import com.supdevinci.fizzquiz.models.database.LeaderboardEntity
import kotlinx.coroutines.flow.Flow

class LeaderboardViewModel(application: Application) : AndroidViewModel(application) {
    private val scoreDao = QuizDatabase.getDatabase(application).scoreDao()
    val leaderboard: Flow<List<LeaderboardEntity>> = scoreDao.getLeaderboard()
}