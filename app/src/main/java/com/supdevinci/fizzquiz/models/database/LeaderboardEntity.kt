package com.supdevinci.fizzquiz.models.database

import java.util.Date

class LeaderboardEntity (
    val username: String,
    val score: Int,
    val scoredAt: Date
)