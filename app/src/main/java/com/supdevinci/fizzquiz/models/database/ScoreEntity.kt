package com.supdevinci.fizzquiz.models.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import java.util.Date

@Entity(
    tableName = "scores",
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["id"],
        childColumns = ["user_id"]
    )]
)
class ScoreEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "user_id")
    val userId: Int,
    val score: Int,
    @SerialName("created_at")
    var createdAt: Date = Date()
)