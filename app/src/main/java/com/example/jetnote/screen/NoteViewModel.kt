package com.example.jetnote.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnote.model.Note
import com.example.jetnote.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository): ViewModel() {
    private var _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged()
                .collect{listOfNotes ->
                    if (listOfNotes.isEmpty()) {

                    } else {
                      _noteList.value = listOfNotes
                    }

                }
        }

    }

     fun addNote(note: Note) = viewModelScope.launch{repository.addNote(note) }

     fun updateNote(note: Note) = viewModelScope.launch{repository.updateNote(note) }

     fun removeNote(note: Note) = viewModelScope.launch{repository.deleteNote(note) }

}