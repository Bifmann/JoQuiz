package com.example.joquiz

data class QuizList(val title: String, val questions: List<Question>)

data class Question(
    val text: String,
    val trueAnswer: String,
    val wrongAnswer1: String,
    val wrongAnswer2: String
)