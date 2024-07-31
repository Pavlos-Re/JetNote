package com.expressdigibooks.android.ui.interfaces

import java.io.File

interface AudioRecorder {
    fun start(outputFile: File)
    fun stop()
}