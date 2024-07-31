package com.example.testspeech

//import android.Manifest
//import android.app.Activity
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Button
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.core.app.ActivityCompat
//import java.io.File
//
//class MainActivity : ComponentActivity() {
//
//    private val recorder by lazy {
//       AndroidAudioRecorder(applicationContext)
//    }
//
//    private val player by lazy {
//        AndroidAudioPlayer(applicationContext)
//    }
//
//    private var audioFile: File? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        ActivityCompat.requestPermissions(
//            this,
//            arrayOf(Manifest.permission.RECORD_AUDIO),
//            0
//        )
//        setContent {
//            TestSpeechTheme {
//                Column(
//                    modifier = Modifier.fillMaxSize(),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Button(onClick = {
//                        File(cacheDir, "audio.mp3").also {
//                            recorder.start(it)
//                            audioFile = it
//                            }
//                        }) {
//                        Text(text = "Start recording")
//                    }
//                    Button(onClick = {
//                        recorder.stop()
//                    }) {
//                        Text(text = "Stop recording")
//                    }
//                    Button(onClick = {
//                        player.playFile(audioFile ?: return@Button)
//                    }) {
//                        Text(text = "Start playing")
//                    }
//                    Button(onClick = {
//                        player.stop()
//                    }) {
//                        Text(text = "Stop playing")
//                    }
//                }
//            }
//        }
//    }
//}
