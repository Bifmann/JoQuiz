// Déclaration du package et importation des bibliothèques nécessaires.
package com.example.joquiz

import kotlinx.coroutines.launch
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// Déclaration de la classe de l'activité, héritant de AppCompatActivity.
class Questions : AppCompatActivity() {
    // Déclaration et initialisation tardive des éléments de l'interface utilisateur.
    private lateinit var buttonReponse1: Button
    private lateinit var buttonReponse2: Button
    private lateinit var buttonReponse3: Button
    private lateinit var title: TextView
    private lateinit var question: TextView

    // Fonction appelée à la création de l'activité.
    override fun onCreate(savedInstanceState: Bundle?) {
        // Récupération des identifiants du quiz et de la question depuis l'intent.
        val idQuiz = intent.getIntExtra("ID_QUIZ", -1)
        val idQuestion = intent.getIntExtra("ID_QUESTION", -1)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.questions) // Définition du layout de l'activité.

        val quizRepository = QuizRepository(this) // Création d'une instance de QuizRepository.
        val data = quizRepository.getAllQuizzes() // Récupération de tous les quizzes.
        val quiz: QuizList = data[idQuiz] // Sélection du quiz actuel selon l'id.

        // Initialisation des éléments de l'interface utilisateur.
        buttonReponse1 = findViewById(R.id.button_reponse1)
        buttonReponse2 = findViewById(R.id.button_reponse2)
        buttonReponse3 = findViewById(R.id.button_reponse3)
        title = findViewById(R.id.textview_TitreQuiz)
        question = findViewById(R.id.textview_Question)

        // Mise à jour de l'interface utilisateur avec les données du quiz.
        title.text = quiz.title
        question.text = quiz.questions[idQuestion].text

        buttonOrder(quiz, idQuestion) // Mélange et affichage des options de réponse.

        setupButtons() // Configuration des écouteurs d'événements pour les boutons.
    }

    // Configuration des boutons de réponse.
    private fun setupButtons() {
        // Récupération des identifiants du quiz et de la question, et initialisation de la liste des réponses.
        val idQuiz = intent.getIntExtra("ID_QUIZ", -1)
        val idQuestion = intent.getIntExtra("ID_QUESTION", -1)
        val listeBooleansMutable =
            intent.getBooleanArrayExtra("LISTE_BOOLEAN_REPONSE")?.toMutableList() ?: mutableListOf()
        val navigationHandler =
            NavigationHandler(this) // Création d'une instance de NavigationHandler.

        val quizRepository =
            QuizRepository(this) // Accès au repository pour récupérer les données du quiz.
        val data = quizRepository.getAllQuizzes()
        val quiz: QuizList = data[idQuiz]

        // Configuration des actions pour chaque bouton de réponse.
        nextQuiz(quiz, buttonReponse1, listeBooleansMutable, idQuiz, idQuestion, navigationHandler)
        nextQuiz(quiz, buttonReponse2, listeBooleansMutable, idQuiz, idQuestion, navigationHandler)
        nextQuiz(quiz, buttonReponse3, listeBooleansMutable, idQuiz, idQuestion, navigationHandler)
    }

    // Fonction pour gérer les actions lors du clic sur un bouton de réponse.
    private fun nextQuiz(
        quiz: QuizList,
        button: Button,
        liste: MutableList<Boolean>,
        idQuiz: Int,
        idQuestion: Int,
        nav: NavigationHandler,
    ) {
        val db = AppDatabase.getDatabase(this) // Accès à la base de données.
        val scoreDao = db.scoreDao() // Accès au DAO pour les scores.

        button.setOnClickListener {
            liste.add(button.tag == true) // Ajout de la réponse à la liste.
            val updatedList = liste.toBooleanArray()

            if (idQuestion != quiz.questions.size - 1) {
                // Si ce n'est pas la dernière question, passer à la suivante.
                nav.quizHandler(this, idQuiz, idQuestion + 1, updatedList)
            } else {
                // Sinon, calculer le score et insérer le résultat dans la base de données.
                val trueCount = updatedList.count { it }
                val newScore = ScoreResult(title = quiz.title, score = trueCount)

                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        scoreDao.insert(newScore) // Insertion du score.
                    }
                    val allScores = withContext(Dispatchers.IO) {
                        scoreDao.getAllScores() // Récupération de tous les scores.
                    }
                }
                nav.goToResult(this, idQuiz, updatedList) // Navigation vers l'écran de résultats.
            }
        }
    }

    // Fonction pour mélanger et attribuer les réponses aux boutons.
    private fun buttonOrder(quiz: QuizList, idQuestion: Int) {
        // Création et mélange de la liste des réponses.
        val questionsOrdre = listOf(
            Pair(quiz.questions[idQuestion].trueAnswer, true),
            Pair(quiz.questions[idQuestion].wrongAnswer1, false),
            Pair(quiz.questions[idQuestion].wrongAnswer2, false)
        ).shuffled()

        // Attribution des réponses aux boutons.
        buttonReponse1.text = questionsOrdre[0].first
        buttonReponse2.text = questionsOrdre[1].first
        buttonReponse3.text = questionsOrdre[2].first

        // Enregistrement de l'état de chaque réponse pour utilisation ultérieure.
        buttonReponse1.tag = questionsOrdre[0].second
        buttonReponse2.tag = questionsOrdre[1].second
        buttonReponse3.tag = questionsOrdre[2].second
    }
}
