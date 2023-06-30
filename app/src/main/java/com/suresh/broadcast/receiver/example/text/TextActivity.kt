package com.suresh.broadcast.receiver.example.text

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.suresh.broadcast.receiver.example.R
import com.suresh.broadcast.receiver.example.base.BaseActivity
import com.suresh.broadcast.receiver.example.databinding.ActivityTextBinding
import com.suresh.broadcast.receiver.example.util.TEXT

class TextActivity : BaseActivity() {
    lateinit var binding: ActivityTextBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_text)
        binding.apply {
            txtText.text = intent.getStringExtra(TEXT)
        }
    }
}