package com.supdevinci.fizzquiz.models.database

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import java.util.Date

@Entity(
    tableName = "users",
    indices = [Index(value = ["username"], unique = true)]
)
class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val username: String,
    @SerialName("created_at")
    var createdAt: Date = Date(),
    @SerialName("updated_at")
    var updatedAt: Date = Date()
)