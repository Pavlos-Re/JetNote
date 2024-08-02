package com.example.jetnote.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetnote.speech.AndroidAudioPlayer
import com.example.jetnote.speech.AndroidAudioRecorder
import java.io.File

@Composable
fun SpeechScreen(recorder: AndroidAudioRecorder, player: AndroidAudioPlayer, audioFile: File?) {

    Column(
        modifier = Modifier.padding(6.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.padding(6.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                audioFile.also {
                    if (it != null) {
                        recorder.start(it)
                    }
                }
            }) {
                Text(text = "Start recording")
            }
            Button(onClick = {
                recorder.stop()
            }) {
                Text(text = "Stop recording")
            }
            Button(onClick = {
                player.playFile(audioFile ?: return@Button)
            }) {
                Text(text = "Start playing")
            }
            Button(onClick = {
                player.stop()
            }) {
                Text(text = "Stop playing")
            }
        }
        Button(onClick = { }) {
            Text(text = "Next Option")
        }
    }

}
