package com.example.oblig1_quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Koble XML-layouten som ble laget i layoutmappen
        setContentView(R.layout.activity_main)

        // Finn knappene fra XML
        val btnGallery = findViewById<Button>(R.id.btnGallery)
        val btnQuiz = findViewById<Button>(R.id.btnQuiz)

        // Når Gallery trykkes
        btnGallery.setOnClickListener {
            startActivity(Intent(this, GalleryActivity::class.java)) //Åpner gallery
        }

        // Når Quiz trykkes
        btnQuiz.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java)) //Åpner quiz
        }
    }
}

