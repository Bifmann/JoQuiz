package com.example.joquiz
import androidx.room.Entity
import androidx.room.PrimaryKey

data class QuizList(val title: String, val questions: List<Question>)

data class Question(
    val text: String,
    val trueAnswer: String,
    val wrongAnswer1: String,
    val wrongAnswer2: String
)

@Entity(tableName = "score_table")
data class Score(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val score: Int
)
