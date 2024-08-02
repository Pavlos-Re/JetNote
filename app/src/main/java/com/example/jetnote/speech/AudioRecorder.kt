package com.example.jetnote.speech

import java.io.File

interface AudioRecorder {
    fun start(outputFile: File)
    fun stop()
}