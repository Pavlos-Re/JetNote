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
import okhttp3.FormBody
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository): ViewModel() {
    private var _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    var client = OkHttpClient()
    val folder: File =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
    var file = File(folder, "geeksData.txt")


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

         // Storing the data in file with name as geeksData.txt
         file = File(folder, "geeksData.txt")
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
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun run() {
        viewModelScope.launch(Dispatchers.IO) {

//            val formBody = FormBody.Builder()
//                .add("test", "Jurassic Park")
//                .build()
//
//            val request = Request.Builder()
//                .url("http://10.101.176.40:5000/test")
//                .post(formBody)
//                .build()

            //val file = File("geeksData.txt")

            val postBody = _noteList.value.last().description

            val formBody = FormBody.Builder()
                .add("test", postBody)
                .build()

            val request = Request.Builder()
                .url("http://10.101.176.109:5000/test")
                .post(formBody)
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                println(response.body!!.string())
            }
        }
    }

}
