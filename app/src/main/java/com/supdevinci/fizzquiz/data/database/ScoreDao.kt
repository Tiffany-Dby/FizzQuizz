package com.supdevinci.fizzquiz.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.supdevinci.fizzquiz.models.database.LeaderboardEntity
import com.supdevinci.fizzquiz.models.database.ScoreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScoreDao {
    @Insert
    fun insert(score: ScoreEntity): Long

    @Query("""
         SELECT username, score, scores.createdAt as scoredAt
         FROM scores
         JOIN users ON users.id = scores.user_id
         ORDER BY scores.score DESC, scoredAt ASC
    """)
    fun getLeaderboard(): Flow<List<LeaderboardEntity>>
}