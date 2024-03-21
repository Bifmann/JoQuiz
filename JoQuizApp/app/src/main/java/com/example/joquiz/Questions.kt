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


        buttonReponse1 = findViewById(R.id.button_reponse1)
        buttonReponse2 = findViewById(R.id.button_reponse2)
        buttonReponse3 = findViewById(R.id.button_reponse3)

        title = findViewById(R.id.textview_TitreQuiz)
        question = findViewById(R.id.textview_Question)

        title.text = quiz.title
        question.text = quiz.questions[idQuestion].text

        buttonOrder(quiz, idQuestion)

        setupButtons()
    }

    fun setupButtons() {
        val idQuiz = intent.getIntExtra("ID_QUIZ", -1)
        val idQuestion = intent.getIntExtra("ID_QUESTION", -1)
        val listeBooleansMutable = intent.getBooleanArrayExtra("LISTE_BOOLEAN_REPONSE")?.toMutableList() ?: mutableListOf()
        val navigationHandler = NavigationHandler(this)

        val quizRepository = QuizRepository(this)
        val data = quizRepository.getAllQuizzes()
        val quiz: QuizList = data[idQuiz]

        nextQuiz(quiz, buttonReponse1, listeBooleansMutable, idQuiz, idQuestion, navigationHandler)
        nextQuiz(quiz, buttonReponse2, listeBooleansMutable, idQuiz, idQuestion, navigationHandler)
        nextQuiz(quiz, buttonReponse3, listeBooleansMutable, idQuiz, idQuestion, navigationHandler)
    }

    fun nextQuiz(
        quiz: QuizList,
        button: Button,
        liste: MutableList<Boolean>,
        idQuiz: Int,
        idQuestion: Int,
        nav: NavigationHandler
    ) {
        button.setOnClickListener {
            println(quiz.questions.size)
            println(idQuestion)
            liste.add(button.tag == true)
            val updatedList = liste.toBooleanArray()

            if (idQuestion != quiz.questions.size - 1) {
                println(updatedList.contentToString())
                nav.QuizHandler(this, idQuiz, idQuestion + 1, updatedList)
            } else {
                println(updatedList.contentToString())
                nav.goToResult(this, idQuiz, updatedList)
            }
        }
    }

    fun buttonOrder(quiz: QuizList, idQuestion: Int) {
        // Création d'une liste de paires (String, Boolean) où le Boolean indique si c'est la bonne réponse
        val questionsOrdre = listOf(
            Pair(quiz.questions[idQuestion].trueAnswer, true),
            Pair(quiz.questions[idQuestion].wrongAnswer1, false),
            Pair(quiz.questions[idQuestion].wrongAnswer2, false)
        ).shuffled() // Mélanger la liste pour randomiser l'ordre des réponses

        // Affecter les réponses mélangées aux boutons
        buttonReponse1.text = questionsOrdre[0].first
        buttonReponse2.text = questionsOrdre[1].first
        buttonReponse3.text = questionsOrdre[2].first

        // Stocker les booléens quelque part si nécessaire pour une utilisation ultérieure
        buttonReponse1.tag = questionsOrdre[0].second
        buttonReponse2.tag = questionsOrdre[1].second
        buttonReponse3.tag = questionsOrdre[2].second
    }

}