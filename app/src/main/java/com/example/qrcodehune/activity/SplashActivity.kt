package com.example.qrcodehune.activity

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

import androidx.databinding.DataBindingUtil
import com.example.qrcodehune.R
import com.example.qrcodehune.databinding.ActivitySplashBinding



class SplashActivity : AppCompatActivity() {
    private var binding: ActivitySplashBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        goToMainActivity()
    }

    private fun goToMainActivity() {
        Handler().postDelayed(Runnable {
            startActivity(Intent(this, MainActivity::class.java))
            finish()}, 1000)

    }
}