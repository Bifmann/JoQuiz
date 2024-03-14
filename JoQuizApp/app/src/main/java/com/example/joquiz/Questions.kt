package com.example.joquiz

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Questions : AppCompatActivity() {
    private lateinit var buttonReponse1: Button
    private lateinit var buttonReponse2: Button
    private lateinit var buttonReponse3: Button
    private lateinit var title: TextView
    private lateinit var question: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.questions)
        val quizRepository = QuizRepository(this)
        val data = quizRepository.getAllQuizzes()
        val idQuiz = intent.getIntExtra("ID_QUIZ", -1) //charge l'id du quiz
        val quiz: QuizList = data[idQuiz] // Get the data model based on position.

        buttonReponse1 = findViewById(R.id.button_reponse1)
        buttonReponse2 = findViewById(R.id.button_reponse2)
        buttonReponse3 = findViewById(R.id.button_reponse3)
        title = findViewById(R.id.textview_TitreQuiz)
        question = findViewById(R.id.textview_Question)

        title.text = quiz.title
        setupButtons()
    }


    fun setupButtons() {
        val navigationHandler = NavigationHandler(this)
        navigationHandler.setupButtonNavigation(buttonReponse1, MainActivity::class.java)
        navigationHandler.setupButtonNavigation(buttonReponse2, MainActivity::class.java)
        navigationHandler.setupButtonNavigation(buttonReponse3, MainActivity::class.java)
    }
}