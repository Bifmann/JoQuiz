package com.example.joquiz

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Historique : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.historique)

        val buttonRetourMenu: Button = findViewById(R.id.button_retourMenu)
        val navigationHandler = NavigationHandler(this)

        // Configuration du bouton pour retourner au menu principal.
        navigationHandler.setupButtonNavigation(buttonRetourMenu, MainActivity::class.java)

        // Chargement des données de l'historique de manière asynchrone.
        loadHistoriqueDataAsync()
    }

    private fun loadHistoriqueDataAsync() {
        // Utilisation de lifecycleScope pour exécuter la coroutine.
        lifecycleScope.launch {
            val data = getDataFromDBAsync()
            setupRecyclerView(data)
        }
    }

    // Méthode asynchrone pour récupérer les données de la base de données.
    private suspend fun getDataFromDBAsync(): List<ScoreResult> = withContext(Dispatchers.IO) {
        val db = AppDatabase.getDatabase(this@Historique)
        val scoreDao = db.scoreDao()
        scoreDao.getAllScores()
    }

    // Configuration de RecyclerView avec les données chargées.
    private fun setupRecyclerView(data: List<ScoreResult>) {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView_Historique)
        recyclerView.adapter = HistoriqueAdapter(this, data)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
