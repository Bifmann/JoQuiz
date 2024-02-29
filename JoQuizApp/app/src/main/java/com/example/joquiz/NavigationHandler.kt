package com.example.joquiz
import android.content.Context
import android.content.Intent
import android.widget.Button

class NavigationHandler (private val context: Context) {

    fun setupButtonNavigation(button: Button, destinationClass: Class<*>) {
        button.setOnClickListener {
            val intent = Intent(context, destinationClass)
            context.startActivity(intent)
        }
    }
}
