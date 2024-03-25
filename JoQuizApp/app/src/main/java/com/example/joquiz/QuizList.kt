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

