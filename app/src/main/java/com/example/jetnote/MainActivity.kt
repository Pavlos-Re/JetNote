package com.example.jetnote

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.example.jetnote.ui.theme.JetNoteTheme
import com.example.jetnote.speech.AndroidAudioPlayer
import com.example.jetnote.speech.AndroidAudioRecorder
import java.io.File
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewModelScope
import com.example.jetnote.screen.NoteScreen
import com.example.jetnote.screen.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val recorder by lazy {
        AndroidAudioRecorder(applicationContext)
    }

    private val player by lazy {
        AndroidAudioPlayer(applicationContext)
    }

    private var audioFile: File? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        audioFile = File(cacheDir, "audio.mp3")
        val noteViewModel: NoteViewModel by viewModels()

        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.RECORD_AUDIO),
            0
        )
        setContent {
            JetNoteTheme {
                //SpeechRec(recorder, player, audioFile)
                NotesApp(noteViewModel)
            }
        }
    }
}

@Composable
fun SpeechRec(recorder: AndroidAudioRecorder, player: AndroidAudioPlayer, audioFile: File?) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
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
}

@Composable
fun NotesApp(noteViewModel: NoteViewModel) {
    val notesList = noteViewModel.noteList.collectAsState().value

    NoteScreen(
        notes = notesList,
        onRemoveNote = {
            noteViewModel.removeNote(it)
        },
        onAddNote = {
            noteViewModel.addNote(it)
        },
        onUpdateNote = {
            noteViewModel.viewModelScope.launch {
                noteViewModel.updateNote(it)
            }
        }
    )

}
