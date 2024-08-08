package com.example.jetnote.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetnote.R
import com.example.jetnote.components.NoteButton
import com.example.jetnote.components.NoteInputText
import com.example.jetnote.model.Note
import com.example.jetnote.ui.theme.JetNoteTheme
import com.example.jetnote.util.formatDate

@Composable
fun NoteScreen(
    noteViewModel: NoteViewModel,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit,
    onUpdateNote: (Note) -> Unit,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier
) {

    val notesList = noteViewModel.noteList.collectAsState().value

    val notes: List<Note> = notesList

    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    Column(modifier,

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {

        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        }, actions = {
            Icon(imageVector = Icons.Rounded.Notifications,
                contentDescription = "Icon")
        },
        backgroundColor = Color(0xFFDADFE3))

        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween) {

            NoteInputText(modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = title,
                label = "Title",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) title = it
                })

            NoteInputText(modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = description,
                label = "Add a note",
                onTextChange = {
                    if (it.all { char ->
                            char.isDefined()
                        }) description = it
                })

            NoteButton(text = "Save",
                onClick = {
                    if (title.isNotEmpty() && description.isNotEmpty()) {
                        onAddNote(Note(title = title, description = description))
                          title = ""
                          description = ""
                        Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                    }
                })

        }
        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn{
            items(notes) { note ->
                NoteRow(note = note, onNoteClicked = {})
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End, modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)) {
                    Icon(imageVector = Icons.Rounded.Delete, contentDescription = "Delete Icon", modifier = Modifier.clickable { onRemoveNote(note) })
                    Icon(imageVector = Icons.Rounded.Build, contentDescription = "Update Icon", modifier = Modifier.clickable { }
                    )
                }
            }
        }
        Column(
            modifier = Modifier.fillMaxSize().padding(bottom = 10.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Button(onClick = { onNextButtonClicked() }) {
                    Text(text = "Back")
                }
        }
    }

}

@Composable
fun NoteRow(modifier : Modifier = Modifier,
            note: Note,
            onNoteClicked: (Note) -> Unit) {

    Surface(
        modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        elevation = 6.dp) {
        Column(
            modifier
                .clickable { }
                .padding(horizontal = 14.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.Start) {
            Text(note.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(note.description,
                style = MaterialTheme.typography.subtitle2)
            Text(text = formatDate(note.entryDate.time),
                style = MaterialTheme.typography.caption)
        }
    }

}

fun updateNote(note: Note ): Note {

        note.title = "Updated"
        note.description = "Updated"

        return note
}

