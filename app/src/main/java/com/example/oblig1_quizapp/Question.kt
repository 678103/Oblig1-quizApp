package com.example.oblig1_quizapp
import android.net.Uri
class Question(
    val imageUri: Uri,
    val correctName: String,
    val wrongNames:List<String>
)