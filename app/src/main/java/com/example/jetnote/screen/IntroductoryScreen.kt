package com.example.jetnote.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun IntroductoryScreen(
    modifier: Modifier,
    onChoiceTextButtonClicked: () -> Unit,
    onChoiceSpeechButtonClicked: () -> Unit
) {
    Column(
        modifier = modifier,
        ) {
        TopAppBar(title = { Text(text = "Greetings, please make a choice.") })

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { onChoiceTextButtonClicked() }) {
                androidx.compose.material.Text(text = "Text Option")
            }
            Button(onClick = { onChoiceSpeechButtonClicked() }) {
                androidx.compose.material.Text(text = "Speech Option")
            }
        }

    }

}
