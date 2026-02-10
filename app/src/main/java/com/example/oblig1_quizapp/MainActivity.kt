package com.example.oblig1_quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val btnGallery = findViewById<Button>(R.id.btnGallery)
        val btnQuiz = findViewById<Button>(R.id.btnQuiz)

        btnGallery.setOnClickListener {
            startActivity(Intent(this, GalleryActivity::class.java))
        }

        btnQuiz.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
        }
    }
}

