package com.suresh.broadcast.receiver.example.image

import android.net.Uri
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.suresh.broadcast.receiver.example.base.BaseActivity
import com.suresh.broadcast.receiver.example.R
import com.suresh.broadcast.receiver.example.databinding.ActivityImageBinding
import com.suresh.broadcast.receiver.example.util.IMAGE

class ImageActivity : BaseActivity() {
    lateinit var binding: ActivityImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_image)
        binding.apply {

            val uri = intent.getStringExtra(IMAGE)
            image.setImageURI(Uri.parse(uri))

        }

    }
}