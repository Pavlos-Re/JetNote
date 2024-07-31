package com.example.testapplicationspeech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.testapplicationspeech.databinding.FragmentSecondBinding
import android.content.Context
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Build
import android.os.Environment
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.lifecycleScope
import com.expressdigibooks.android.ui.assignments.speaking.AndroidAudioPlayer
import com.expressdigibooks.android.ui.assignments.speaking.AndroidAudioRecorder
import com.masoudss.lib.WaveformSeekBar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    lateinit var progress: WaveformSeekBar

    var duration = 0

    private lateinit var requestAudioRecording: ActivityResultLauncher<String>
    private var isPermissionGranted: Boolean = false


    private val recorder by lazy {
        context?.let { AndroidAudioRecorder(context = it.applicationContext) }
    }

    private val player by lazy {
        context?.let { AndroidAudioPlayer(context = it.applicationContext) }
    }

    private var audioFile: File? = null

    private var progressNow: Float = 0.0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpListeners()

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpListeners() {

        binding.buttonStartRec.setOnClickListener {
            File(context?.cacheDir, "audio.mp3").also {
                recorder?.start(it)
                audioFile = it
            }

        }

        binding.buttonStopRec.setOnClickListener {
            recorder?.stop()
            binding.waveform.setSampleFrom(audioFile!!)

        }

        binding.buttonPlay.setOnClickListener {
            if (progressNow.equals(0.0f)) {
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        player?.playFile(audioFile ?: return@withContext)
                        progressNow = 0.0f
                    }
                }
            }
            else {
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        player?.playFile(audioFile ?: return@withContext, progressNow)
                        progressNow = 0.0f
                    }
                }
            }
        }

        binding.buttonStop.setOnClickListener {
            player?.stop()

        }


    }
}