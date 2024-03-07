package com.example.joquiz

import MenuQuizAdapter
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var buttonHistorique: Button
    private lateinit var quizRepository: QuizRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components using lateinit
        recyclerView = findViewById(R.id.recyclerView_MenuQuiz)
        buttonHistorique = findViewById(R.id.button_historique)

        // Initialize QuizRepository using lateinit
        quizRepository = QuizRepository(this)

        // Setup RecyclerView
        setupRecyclerView()

        // Setup button navigation
        setupButtonNavigation()
    }

    /**
     * Set up the RecyclerView with the MenuQuizAdapter and layout manager.
     */
    private fun setupRecyclerView() {
        recyclerView.adapter = MenuQuizAdapter(createData())
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    /**
     * Set up button navigation using the NavigationHandler.
     */
    private fun setupButtonNavigation() {
        val navigationHandler = NavigationHandler(this)
        navigationHandler.setupButtonNavigation(buttonHistorique, Historique::class.java)
    }

    /**
     * Create and return a list of QuizList using the QuizRepository.
     */
    private fun createData(): List<QuizList> {
        return quizRepository.getAllQuizzes()
    }
}
