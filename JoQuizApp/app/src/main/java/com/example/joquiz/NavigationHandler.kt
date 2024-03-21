package com.example.joquiz

import android.content.Context
import android.content.Intent
import android.widget.Button

class NavigationHandler(private val context: Context) {

    fun setupButtonNavigation(button: Button, destinationClass: Class<*>) {
        button.setOnClickListener {
            val intent = Intent(context, destinationClass)
            context.startActivity(intent)
        }
    }

    fun goToFirstQuiz(context: Context, position: Int, positionQuestion: Int) {
        val intent = Intent(context, Questions::class.java).apply {
            putExtra("ID_QUIZ", position)
            putExtra("ID_QUESTION", positionQuestion)
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        context.startActivity(intent)
    }

    fun QuizHandler(context: Context, position: Int, positionQuestion: Int, list: BooleanArray?) {
        val intent = Intent(context, Questions::class.java).apply {
            putExtra("ID_QUIZ", position)
            putExtra("ID_QUESTION", positionQuestion)
            putExtra("LISTE_BOOLEAN_REPONSE", list)

            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        context.startActivity(intent)
    }

    fun goToResult(context: Context, list: BooleanArray?) {
        val intent = Intent(context, Resultat::class.java).apply {
            putExtra("LISTE_BOOLEAN_REPONSE", list)

            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        context.startActivity(intent)
    }

}
