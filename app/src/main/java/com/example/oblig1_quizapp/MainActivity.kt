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
// Forklaring linje-for-linje:
//
//class MainActivity : AppCompatActivity()
//
//Denne Activityen bruker AppCompat fordi vi har XML-layout.
//
//onCreate()
//
//Kalles når Activityen starter.
//
//setContentView(R.layout.activity_main)
//
//Binder XML-layout (activity_main.xml) til denne Activityen.
//
//findViewById<Button>(R.id.btnGallery)
//
//Finner knappen i XML med id btnGallery.
//
//btnGallery.setOnClickListener { ... }
//
//Når brukeren trykker på Gallery-knappen, starter vi GalleryActivity.
//
//Samme for Quiz-knappen.
