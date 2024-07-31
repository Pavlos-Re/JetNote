package com.example.testspeech

import java.io.File

interface AudioRecorder {
    fun start(outputFile: File)
    fun stop()
}