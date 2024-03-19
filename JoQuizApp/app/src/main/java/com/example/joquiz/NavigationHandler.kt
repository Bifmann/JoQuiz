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

    fun QuizHandler(context: Context, button: Button, position: Int, positionQuestion: Int) {
        button.setOnClickListener {
            val intent = Intent(context, Questions::class.java).apply {
                putExtra("ID_QUIZ", position)
                putExtra("ID_QUESTION", positionQuestion)
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            context.startActivity(intent)
        }

    }

    fun goToQuiz(context: Context, position: Int, positionQuestion: Int) {
        val intent = Intent(context, Questions::class.java).apply {
            putExtra("ID_QUIZ", position)
            putExtra("ID_QUESTION", positionQuestion)
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        context.startActivity(intent)
    }


}
