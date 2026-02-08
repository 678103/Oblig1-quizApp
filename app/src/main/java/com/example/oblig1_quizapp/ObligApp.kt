package com.example.oblig1_quizapp

import android.app.Application
import android.net.Uri
import android.content.Context
import androidx.compose.runtime.mutableStateListOf

class ObligApp: Application() {
    val questions = mutableStateListOf<Question>() // her ligger alle quiz-spørsmål. lagres i minne
    var score = 0

    override fun onCreate() {
        super.onCreate()
        loadQuestions()
    }
    private fun drawableToUri(resId: Int, context: Context): Uri {
        return Uri.parse("android.resource://${context.packageName}/$resId") }

    private fun loadQuestions() {

        questions.add(
            Question(
                imageUri = drawableToUri(R.drawable.cat, this),
                correctName = "Katt",
                wrongNames = listOf("Hund", "Hest")
            )
        )

        questions.add(
            Question(
                imageUri = drawableToUri(R.drawable.dog, this),
                correctName = "Hund",
                wrongNames = listOf("Katt", "Hest")
            )
        )
        questions.add(
            Question(
                imageUri = drawableToUri(R.drawable.horse, this),
                correctName = "Hest",
                wrongNames = listOf("Hund", "Katt")
            )
        )
    }

    fun removeQuestion(question: Question){
        questions.remove(question)
    }

    fun addQuestion(uri: Uri, name: String = "Ny") {
        val question = Question(
            imageUri = uri,
            correctName = name,
            wrongNames = emptyList()
        )
        questions.add(question)

    }

    //for å sortere bildene i galleriet alfabetisk
    fun sortPicturesFromLowToHigh() {
        questions.sortBy { it.correctName.lowercase() }
    }

    fun sortPicturesFromHighToLow(){
        questions.sortByDescending { it.correctName.lowercase() }
    }


    }

