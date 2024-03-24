package com.example.joquiz

import androidx.room.Entity
import androidx.room.PrimaryKey

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Entity(tableName = "scores")
data class ScoreResult(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val score: Int
)

@Dao
interface ScoreDao {
    @Insert
    suspend fun insert(score: ScoreResult) // Modifiez ici Score par ScoreResult

    @Query("SELECT * FROM scores")
    fun getAllScores(): List<ScoreResult> // Assurez-vous que cette ligne retourne des ScoreResult aussi
}


@Database(entities = [ScoreResult::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun scoreDao(): ScoreDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "score_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}


