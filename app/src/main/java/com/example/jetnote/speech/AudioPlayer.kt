package com.example.testspeech

import java.io.File

interface AudioPlayer {
    fun playFile(file: File)
    fun stop()
}