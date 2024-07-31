package com.expressdigibooks.android.ui.assignments.speaking

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import com.expressdigibooks.android.ui.interfaces.AudioPlayer
import java.io.File

class AndroidAudioPlayer(
    private val context: Context,
    //private val waveformProgress: WaveformProgress,

): AudioPlayer {

    private var player: MediaPlayer? = null
    var result = 0.0f

    override fun playFile(file: File) {
        //player?.release()

        MediaPlayer.create(context, file.toUri()).apply {
            player = this
            //waveformProgress.updateProgress(player!!.duration)
            start().run {

//                while (player!!.isPlaying) {
//                    viewModel.liveDataProgress.postValue(player!!.currentPosition)
//
//                    }
                }
            //player?.release()
            }

        }

    override fun playFile(file: File, progress: Float) {
        //player?.release()

        MediaPlayer.create(context, file.toUri()).apply {
            player = this
            result =  ((player!!.duration)) * (progress.toInt().toFloat() / 100)
            //waveformProgress.updateProgress(player!!.duration)
            player!!.seekTo(result.toInt())
            player!!.start().run {
//                while (player!!.isPlaying) {
//                    viewModel.liveDataProgress.postValue(player!!.currentPosition)
//
//                }
            }
            result = 0.0f
            //player?.release()
        }

    }

    override fun stop() {
        player?.stop()
        player?.release()
        player = null
    }
}
