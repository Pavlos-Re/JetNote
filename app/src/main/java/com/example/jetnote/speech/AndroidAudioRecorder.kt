package com.expressdigibooks.android.ui.assignments.speaking

import android.content.Context
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Build
import android.os.Environment
import com.expressdigibooks.android.ui.interfaces.AudioRecorder
import java.io.File
import java.io.FileOutputStream

class AndroidAudioRecorder(
    private val context: Context
): AudioRecorder {
    private var recorder: MediaRecorder? = null
    private var audioRecord: AudioRecord? = null
    private var isRecording = false


    private fun createRecorder(): MediaRecorder {

        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(context)
        } else {
            MediaRecorder()
        }

    }

    override fun start(outputFile: File) {

        createRecorder().apply {

            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setAudioSamplingRate(41000)
            setOutputFile(FileOutputStream(outputFile).fd)

            prepare()
            start()

            recorder = this

        }

        val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val outputFile = File(downloadsDir, "audio.mp3")
/*
        val sampleRate = 44100
        val channelConfig = AudioFormat.CHANNEL_IN_MONO
        val audioFormat = AudioFormat.ENCODING_PCM_16BIT
        val bufferSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat)

        if (ActivityCompat.checkSelfPermission(
                this.context,
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        audioRecord = AudioRecord(MediaRecorder.AudioSource.MIC, sampleRate, channelConfig, audioFormat, bufferSize)
        val buffer = ShortArray(bufferSize)

        isRecording = true
        audioRecord?.startRecording()

        //while (isRecording) {
        //    val numBytesRead = audioRecord?.read(buffer, 0, bufferSize) ?: 0
            // Process audio data and calculate amplitude
            //val amplitude = calculateAmplitude(buffer, numBytesRead)
            // Update waveform display
            //updateWaveformDisplay(amplitude)
        //    println("THE RESULT IS: " + numBytesRead)

       // }

 */
    }

    override fun stop() {
        //audioRecord!!.stop()
        //isRecording = false
        recorder?.stop()
        recorder?.reset()
        recorder = null
    }
}
