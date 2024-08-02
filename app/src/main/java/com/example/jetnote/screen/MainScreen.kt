package com.example.jetnote.screen

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetnote.R
import com.example.jetnote.speech.AndroidAudioPlayer
import com.example.jetnote.speech.AndroidAudioRecorder
import kotlinx.coroutines.launch
import java.io.File

enum class JetNoteScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
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
        startDestination = JetNoteScreen.Start.name
    ) {
        composable(route = JetNoteScreen.Start.name) {
            NoteScreen(
                noteViewModel = noteViewModel,
                onAddNote = { noteViewModel.addNote(it) },
                onRemoveNote = { noteViewModel.removeNote(it) },
                onUpdateNote = { noteViewModel.updateNote(it) }) {
                {

                }
            }
            composable(route = JetNoteScreen.Speech.name) {
                SpeechScreen(recorder = recorder, player = player, audioFile = audioFile)
            }
        }
    }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteScreen(
                noteViewModel = noteViewModel,
                onAddNote = { noteViewModel.addNote(it) },
                onRemoveNote = { noteViewModel.removeNote(it) },
                onUpdateNote = { noteViewModel.updateNote(it) }) {

            }
            //SpeechRec(recorder = recorder, player = player, audioFile = audioFile)
        }

    }


//@Composable
//fun NotesApp(noteViewModel: NoteViewModel, navController: NavHostController) {
//    val notesList = noteViewModel.noteList.collectAsState().value
//
//    NoteScreen(
//        noteViewModel,
//        onRemoveNote = {
//            noteViewModel.removeNote(it)
//        },
//        onAddNote = {
//            noteViewModel.addNote(it)
//        },
//        onUpdateNote = {
//            noteViewModel.viewModelScope.launch {
//                noteViewModel.updateNote(it)
//            }
//        },
//        onNextButtonClicked = {
//            navController.navigate(JetNoteScreen.Speech.name)
//        }
//    )
//
//}
