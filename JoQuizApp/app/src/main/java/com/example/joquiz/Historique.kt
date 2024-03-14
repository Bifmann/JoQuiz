package com.example.joquiz

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Historique : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.historique)

        val buttonRetourMenu: Button = findViewById(R.id.button_retourMenu)
        val navigationHandler = NavigationHandler(this)
        navigationHandler.setupButtonNavigation(buttonRetourMenu, MainActivity::class.java)
    }
}