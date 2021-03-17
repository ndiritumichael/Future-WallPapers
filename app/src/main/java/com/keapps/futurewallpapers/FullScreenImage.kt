package com.keapps.futurewallpapers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.keapps.futurewallpapers.databinding.ActivityFullScreenImageBinding

class FullScreenImage : AppCompatActivity() {
    private lateinit var binding: ActivityFullScreenImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullScreenImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}