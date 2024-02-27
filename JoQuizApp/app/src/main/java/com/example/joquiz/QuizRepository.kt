package com.example.joquiz

class QuizRepository {
    private val quizList = mutableListOf<QuizList>()

    init {
        quizList.add(QuizList("Quiz 1"))
        quizList.add(QuizList("JOSHUA 72"))
        quizList.add(QuizList("Quiz 3"))
    }

    fun getAllQuizzes(): List<QuizList> {
        return quizList.toList()
    }

    fun addQuiz(quiz: QuizList) {
        quizList.add(quiz)
    }

    fun removeQuiz(quiz: QuizList) {
        quizList.remove(quiz)
    }
}

