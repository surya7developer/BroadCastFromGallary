package com.suresh.broadcast.receiver.example.video

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import androidx.databinding.DataBindingUtil
import com.suresh.broadcast.receiver.example.base.BaseActivity
import com.suresh.broadcast.receiver.example.R
import com.suresh.broadcast.receiver.example.databinding.ActivityVideoBinding
import com.suresh.broadcast.receiver.example.util.VIDEO

class VideoActivity : BaseActivity() {

    lateinit var binding: ActivityVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding =  DataBindingUtil.setContentView(this,R.layout.activity_video)
        val mediaController = MediaController(this)

        binding.apply {
            video.apply {
                val uri = intent.getStringExtra(VIDEO)
                setVideoURI(Uri.parse(uri))
                mediaController.setAnchorView(this)
                mediaController.setMediaPlayer(this)
                setMediaController(mediaController)
                requestFocus()
                start()
            }
        }
    }
}