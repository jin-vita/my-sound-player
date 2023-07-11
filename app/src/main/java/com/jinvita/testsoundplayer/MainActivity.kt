package com.jinvita.testsoundplayer

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jinvita.testsoundplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isNormal = true
    private val mediaPlayer1 by lazy { MediaPlayer.create(this, R.raw.normal) }
    private val mediaPlayer2 by lazy { MediaPlayer.create(this, R.raw.traffic) }
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer1.release()
        mediaPlayer2.release()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setMediaPlayer()
        mediaPlayer1.start()
        with(binding) {
            buttonOne.setOnClickListener {
                isNormal = true
                binding.textTitle.text = if (mediaPlayer1.isPlaying) "이미 1번 재생 중입니다" else "1번 재생 예약"
            }
            buttonTwo.setOnClickListener {
                isNormal = false
                binding.textTitle.text = if (mediaPlayer2.isPlaying) "이미 2번 재생 중입니다" else "2번 재생 예약"
            }
        }
    }

    private fun setMediaPlayer() {
        mediaPlayer1.setOnCompletionListener {
            if (isNormal) it.start() else {
                it.stop()
                it.prepare()
                mediaPlayer2.start()
                binding.textTitle.text = "2번 재생 중"
            }
        }
        mediaPlayer2.setOnCompletionListener {
            if (!isNormal) it.start() else {
                it.stop()
                it.prepare()
                mediaPlayer1.start()
                binding.textTitle.text = "1번 재생 중"
            }
        }
    }
}