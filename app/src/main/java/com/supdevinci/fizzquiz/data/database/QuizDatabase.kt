package com.supdevinci.fizzquiz.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.supdevinci.fizzquiz.models.database.ScoreEntity
import com.supdevinci.fizzquiz.models.database.UserEntity
import com.supdevinci.fizzquiz.utils.DateConverter

@Database(entities = [UserEntity::class, ScoreEntity::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class QuizDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun scoreDao(): ScoreDao

    companion object {
        @Volatile
        private var INSTANCE: QuizDatabase? = null

        fun getDatabase(context: Context): QuizDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuizDatabase::class.java,
                    "quiz_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }

}