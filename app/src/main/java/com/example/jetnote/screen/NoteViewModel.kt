package com.example.jetnote.screen

import android.os.Environment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnote.model.Note
import com.example.jetnote.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository): ViewModel() {
    private var _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged()
                .collect{listOfNotes ->
                    if (listOfNotes.isEmpty()) {
                      _noteList.value = listOfNotes
                    } else {
                      _noteList.value = listOfNotes
                    }

                }
        }

    }

     fun addNote(note: Note) = viewModelScope.launch{
         repository.addNote(note)

         // getExternalStoragePublicDirectory() represents root of external storage, we are using DOWNLOADS
         // We can use following directories: MUSIC, PODCASTS, ALARMS, RINGTONES, NOTIFICATIONS, PICTURES, MOVIES
         val folder: File =
             Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

         // Storing the data in file with name as geeksData.txt
         val file = File(folder, "geeksData.txt")
         writeTextData(file, note.description, context = currentCoroutineContext())
         // displaying a toast message
     }

     fun updateNote(note: Note) = viewModelScope.launch{repository.updateNote(note) }

     fun removeNote(note: Note) = viewModelScope.launch{repository.deleteNote(note) }

    private fun writeTextData(file: File, data: String, context: CoroutineContext) {
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = FileOutputStream(file)
            fileOutputStream.write(data.toByteArray())
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close()
                    println("BYE BYE")
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        }

}
