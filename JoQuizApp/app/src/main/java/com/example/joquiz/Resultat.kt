package com.example.joquiz

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class Resultat : AppCompatActivity() {

    private lateinit var buttonRecommancer: Button
    private lateinit var buttonVoirReponse: Button
    private lateinit var buttonRetourMenu: Button
    private lateinit var score: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        val listeResultatBoolean = intent.getBooleanArrayExtra("LISTE_BOOLEAN_REPONSE")?.toMutableList() ?: mutableListOf()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.resultat)
        buttonRecommancer = findViewById(R.id.button_recommancer)
        buttonVoirReponse = findViewById(R.id.button_voirReponse)
        buttonRetourMenu = findViewById(R.id.button_retourMenu)

        score = findViewById(R.id.textview_Score)
        val textScore = "score : ${countTrueValues(listeResultatBoolean)} / ${listeResultatBoolean.count()}"
        score.text = textScore
        setupButtons()
    }



    private fun setupButtons() {
        val idQuiz = intent.getIntExtra("ID_QUIZ", -1)
        val navigationHandler = NavigationHandler(this)


        buttonRecommancer.setOnClickListener {
            navigationHandler.goToFirstQuiz(this, idQuiz)
        }

        navigationHandler.setupButtonNavigation(buttonRetourMenu, MainActivity::class.java)
    }

    private fun countTrueValues(booleanArray: MutableList<Boolean>): Int {
        return booleanArray.count { it }
    }
}