package com.example.oblig1_quizapp

import android.content.Intent
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.foundation.Image
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GalleryScreen(
    questions: List<Question>,
    onRemove: (Question) -> Unit,
    onAddImage: (Uri) -> Unit,
    //for å sortere:
    onSortAscending: () -> Unit,
    onSortDescending: () -> Unit
) {
    val context = LocalContext.current

    // Lancher som SAF for å hente bilder
    val pickImage = rememberLauncherForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        if (uri != null) {
            // Gi appen vedvarende lesetilgang, selv etter restart
            context.contentResolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            onAddImage(uri)
        }
    }
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Gallery", fontSize = 24.sp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onSortAscending) { Text("Sorter fra A-Å") }
            Button(onClick = onSortDescending) { Text("Sorter fra Å-A") }
        }

        //Her kommer knappen for å legge til bilder fra brukeren
        Button(
            onClick = { pickImage.launch(arrayOf("image/*")) },
            modifier = Modifier.fillMaxWidth()
        ){
            Text("Legg til bilde")
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(questions, key = { it.imageUri }) { question ->

                val bitmap = remember(question.imageUri) {
                    question.imageUri?.let { uri ->
                        context.contentResolver.openInputStream(uri)?.use {
                            BitmapFactory.decodeStream(it)
                        }
                    }
                }
                bitmap?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = question.correctName,
                        modifier = Modifier.size(80.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = question.correctName, fontSize = 18.sp)
                Spacer(modifier = Modifier.weight(1f))

                Button(onClick = { onRemove(question) }) {
                    Text("Fjern")
                }
            }
        }
    }}

