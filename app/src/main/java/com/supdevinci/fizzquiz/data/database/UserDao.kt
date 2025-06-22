package com.supdevinci.fizzquiz.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.supdevinci.fizzquiz.models.database.UserEntity

@Dao
interface UserDao {
    @Insert
    fun insert(user: UserEntity): Long

    @Query("""
        SELECT * 
        FROM users 
        WHERE username = :username
    """)
    fun getByUsername(username: String): UserEntity?
}