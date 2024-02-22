package com.example.joquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonHistorique : Button = findViewById(R.id.button_historique)
        val navigationHandler = NavigationHandler(this)
        navigationHandler.setupButtonNavigation(buttonHistorique, Historique::class.java)
    }
}


