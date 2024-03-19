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
        val idQuiz = intent.getIntExtra("ID_QUIZ", -1)
        val idQuestion = intent.getIntExtra("ID_QUESTION", -1)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.questions)
        val quizRepository = QuizRepository(this)
        val data = quizRepository.getAllQuizzes()
        val quiz: QuizList = data[idQuiz] // Get the data model based on position.
        val trueAnswer = (0..3).random()

        buttonReponse1 = findViewById(R.id.button_reponse1)
        buttonReponse2 = findViewById(R.id.button_reponse2)
        buttonReponse3 = findViewById(R.id.button_reponse3)
        title = findViewById(R.id.textview_TitreQuiz)
        question = findViewById(R.id.textview_Question)

        title.text = quiz.title
        question.text = quiz.questions[idQuestion].text

        buttonReponse1.text = quiz.questions[idQuestion].trueAnswer
        buttonReponse2.text = quiz.questions[idQuestion].wrongAnswer1
        buttonReponse3.text = quiz.questions[idQuestion].wrongAnswer2
        setupButtons()
    }

    fun setupButtons() {
        val idQuiz = intent.getIntExtra("ID_QUIZ", -1)
        val idQuestion = intent.getIntExtra("ID_QUESTION", -1)

        val navigationHandler = NavigationHandler(this)
        navigationHandler.QuizHandler(this,buttonReponse1, idQuiz, idQuestion+1)
        navigationHandler.QuizHandler(this,buttonReponse2, idQuiz, idQuestion+1)
        navigationHandler.QuizHandler(this,buttonReponse3, idQuiz, idQuestion+1)

        buttonReponse1.setOnClickListener {}
        buttonReponse2.setOnClickListener {}
        buttonReponse3.setOnClickListener {}
    }

    fun buttonOrder(quiz: QuizList) {
        val truePlace = (0..2).random()
        var liste = mutableListOf(0, 1, 2)
        liste.removeAt(truePlace)
        buttonReponse1.text = quiz.questions[truePlace].trueAnswer
        val test = (0..1).random()
        if (test = 1){
            buttonReponse2 = quiz.questions[idQuestion].[2]
        }

    }
}