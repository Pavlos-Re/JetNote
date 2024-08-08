package com.example.jetnote.screen

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetnote.R
import com.example.jetnote.speech.AndroidAudioPlayer
import com.example.jetnote.speech.AndroidAudioRecorder
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetnote.ui.theme.JetNoteTheme
import kotlinx.coroutines.launch
import java.io.File
import java.lang.String.valueOf

enum class JetNoteScreen(@StringRes val title: Int) {
    Start(title = R.string.main_screen),
    Text(title = R.string.text_option),
    Speech(title = R.string.speech_option)
}

@Composable
fun MainScreen(
    audioFile: File,
    player: AndroidAudioPlayer,
    recorder: AndroidAudioRecorder,
    noteViewModel: NoteViewModel,
    navController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navController,
        startDestination = JetNoteScreen.Start.name,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = JetNoteScreen.Start.name) {
            IntroductoryScreen(
                modifier = Modifier.fillMaxSize(),
                onChoiceTextButtonClicked = { navController.navigate(JetNoteScreen.Text.name)},
                onChoiceSpeechButtonClicked = { navController.navigate(JetNoteScreen.Speech.name)})
        }
        composable(route = JetNoteScreen.Text.name) {
            NoteScreen(
                noteViewModel = noteViewModel,
                onAddNote = { noteViewModel.addNote(it) },
                onRemoveNote = { noteViewModel.removeNote(it) },
                onUpdateNote = { noteViewModel.updateNote(it) },
                onNextButtonClicked = {
                    navController.navigate(JetNoteScreen.Start.name)
                },
                modifier = Modifier.fillMaxSize())
        }
        composable(route = JetNoteScreen.Speech.name) {
            SpeechScreen(
                recorder = recorder,
                player = player,
                audioFile = audioFile,
                onNextButtonClicked = {
                    navController.navigate(JetNoteScreen.Start.name)
                },
                modifier = Modifier.fillMaxSize())
        }

    }

    }

