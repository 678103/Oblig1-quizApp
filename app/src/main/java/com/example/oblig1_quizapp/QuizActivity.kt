package com.example.oblig1_quizapp

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.asImageBitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class QuizActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val app = application as ObligApp

            var currentQuestionIndex by remember { mutableIntStateOf(0) }
            var showResult by remember { mutableStateOf((false)) }
            var isCorrect by remember {mutableStateOf(false)}
            val question = app.questions[currentQuestionIndex] // tar bare første spørsmål
            val context = LocalContext.current
            val bitmap = remember(question.imageUri) {
                question.imageUri?.let {
                    uri -> context.contentResolver.openInputStream(uri)?.use {
                    BitmapFactory.decodeStream(it)
                }
                }
            }

            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Score: ${app.score}", fontSize = 20.sp)


                bitmap?.let { bmp ->
                    Image(
                        bitmap = bmp.asImageBitmap(),
                        contentDescription = question.correctName,
                        modifier = Modifier.size(200.dp)
                    )
                }
                val answers =
                    remember(question) { (question.wrongNames + question.correctName).shuffled() }
                answers.forEach { answer ->
                    Button(
                        onClick = {
                            isCorrect = answer == question.correctName
                            showResult = true
                            if (isCorrect) app.score++
                        },
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                    ) {
                        Text(answer)
                    }
                }

                if (showResult) {
                    Text(if (isCorrect) "Riktig!" else "Feil! Riktig svar: ${question.correctName}")

                    Button(onClick = {
                        currentQuestionIndex = (currentQuestionIndex + 1) % app.questions.size
                        showResult = false
                    }) {
                        Text("Neste spørsmål")
                    }
                }
            }
        }
    }
}


