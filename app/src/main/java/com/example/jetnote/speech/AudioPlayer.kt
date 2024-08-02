package com.example.jetnote.speech

import java.io.File

interface AudioPlayer {
    fun playFile(file: File)
    fun stop()
}