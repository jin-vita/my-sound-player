package com.jinvita.mysoundplayer

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jinvita.mysoundplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaPlayer1: MediaPlayer
    private lateinit var mediaPlayer2: MediaPlayer
    private val mediaPlayer11 by lazy {
        MediaPlayer.create(this, R.raw.traffic).apply { setOnCompletionListener { start() } }
    }
    private val mediaPlayer22 by lazy {
        MediaPlayer.create(this, R.raw.traffic2).apply { setOnCompletionListener { start() } }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeMediaPlayer()

        binding.buttonOne.setOnClickListener { playOrPauseAudio11() }
        binding.buttonTwo.setOnClickListener { playOrPauseAudio22() }
    }

    private fun initializeMediaPlayer() {
        mediaPlayer1 = MediaPlayer.create(this, R.raw.sample1)
        mediaPlayer2 = MediaPlayer.create(this, R.raw.sample2)
        mediaPlayer1.setOnCompletionListener { binding.buttonOne.text = "1번 재생 완료" }
        mediaPlayer2.setOnCompletionListener { binding.buttonTwo.text = "2번 재생 완료" }
    }

    private fun playOrPauseAudio11() {
        if (mediaPlayer11.isPlaying) {
            mediaPlayer11.pause()
            binding.buttonOne.text = "1번 일시 정지"
        } else {
            mediaPlayer11.start()
            binding.buttonOne.text = "1번 재생 중"
        }

    }

    private fun playOrPauseAudio22() {
        if (mediaPlayer22.isPlaying) {
            mediaPlayer22.stop()
            mediaPlayer22.prepare()
            binding.buttonTwo.text = "2번 정지"
        } else {
            mediaPlayer22.start()
            binding.buttonTwo.text = "2번 재생 중"
        }
    }

    private fun playOrPauseAudio1() {
        if (mediaPlayer1.isPlaying) {
            mediaPlayer1.pause()
            binding.buttonOne.text = "1번 일시 정지"
        } else {
            mediaPlayer1.start()
            binding.buttonOne.text = "1번 재생 중"
        }

    }


    private fun playOrPauseAudio2() {
        if (mediaPlayer2.isPlaying) {
            mediaPlayer2.stop()
            mediaPlayer2.prepare()
            binding.buttonTwo.text = "2번 정지"
        } else {
            mediaPlayer2.start()
            binding.buttonTwo.text = "2번 재생 중"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer11.release()
        mediaPlayer22.release()
        mediaPlayer1.release()
        mediaPlayer2.release()
    }
}