package com.example.jetnote.data

import com.example.jetnote.model.Note

class NotesDataSource {
    fun loadNotes(): List<Note> {
        return listOf(
            Note(title = "A good day", description = "Went to the movies"),

            Note(title = "A good day", description = "Went to the movies"),

            Note(title = "A good day", description = "Went to the movies"),

            Note(title = "A good day", description = "Went to the movies"),

            Note(title = "A good day", description = "Went to the movies"),

            Note(title = "A good day", description = "Went to the movies"),

            Note(title = "A good day", description = "Went to the movies")

        )
    }
}