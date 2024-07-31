package com.expressdigibooks.android.ui.interfaces

import java.io.File

interface AudioPlayer {
    fun playFile(file: File)

    fun playFile(file: File, progress: Float)

    fun stop()
}