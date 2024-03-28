package com.example.joquiz

import android.content.Context
import android.content.Intent
import android.widget.Button

// Définition de la classe NavigationHandler pour gérer la navigation entre les activités de l'application.
class NavigationHandler(private val context: Context) {

    // Méthode pour configurer la navigation à partir d'un bouton vers une activité spécifiée.
    fun setupButtonNavigation(button: Button, destinationClass: Class<*>) {
        // Définit un écouteur d'événements de clic sur le bouton.
        button.setOnClickListener {
            // Crée une intention pour démarrer l'activité de destination.
            val intent = Intent(context, destinationClass)
            // Démarre l'activité spécifiée.
            context.startActivity(intent)
        }
    }

    // Méthode pour naviguer vers la première question d'un quiz spécifique.
    fun goToFirstQuiz(context: Context, position: Int) {
        val positionQuestion = 0 // Définit la première question du quiz.
        // Prépare une intention pour démarrer l'activité des questions du quiz.
        val intent = Intent(context, Questions::class.java).apply {
            // Ajoute l'ID du quiz et la position de la question comme données extras.
            putExtra("ID_QUIZ", position)
            putExtra("ID_QUESTION", positionQuestion)
            // Configure l'intention pour effacer la pile d'activités actuelle.
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        // Démarre l'activité avec les données fournies.
        context.startActivity(intent)
    }

    // Méthode pour gérer la navigation et le passage de l'état du quiz entre les questions.
    fun quizHandler(context: Context, position: Int, positionQuestion: Int, list: BooleanArray?) {
        // Prépare une intention pour démarrer l'activité des questions avec l'état du quiz.
        val intent = Intent(context, Questions::class.java).apply {
            // Ajoute l'ID du quiz, la position de la question, et l'état des réponses.
            putExtra("ID_QUIZ", position)
            putExtra("ID_QUESTION", positionQuestion)
            putExtra("LISTE_BOOLEAN_REPONSE", list)
            // Efface la pile d'activités actuelle pour cette navigation.
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        // Démarre l'activité des questions du quiz avec les données d'état.
        context.startActivity(intent)
    }

    // Méthode pour naviguer vers l'activité de résultat du quiz avec les résultats.
    fun goToResult(context: Context, position: Int, list: BooleanArray?) {
        // Prépare une intention pour démarrer l'activité des résultats du quiz.
        val intent = Intent(context, Resultat::class.java).apply {
            // Ajoute l'ID du quiz et l'état des réponses comme données extras.
            putExtra("ID_QUIZ", position)
            putExtra("LISTE_BOOLEAN_REPONSE", list)
            // Efface la pile d'activités actuelle pour cette navigation.
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        // Démarre l'activité des résultats avec les données fournies.
        context.startActivity(intent)
    }
}
