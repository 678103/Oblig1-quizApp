package com.example.oblig1_quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class GalleryActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val app = application as ObligApp

        setContent {
            GalleryScreen(
                questions = app.questions,
                onRemove = { question -> app.removeQuestion(question) },
                onAddImage = { uri -> app.addQuestion(uri) },
                        onSortAscending = { app.questions.sortBy { it.correctName } },
                onSortDescending = { app.questions.sortByDescending { it.correctName } }
            )
                }
        }
    }

