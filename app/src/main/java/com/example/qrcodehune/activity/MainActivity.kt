package com.example.qrcodehune.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat


import androidx.databinding.DataBindingUtil
import com.example.qrcodehune.R
import com.example.qrcodehune.databinding.ActivityMainBinding
import com.example.qrcodehune.Fragment.QRCodeFragment.QrCodeFragment

import com.example.qrcodehune.base.ActivityBase.ActivityBase


class MainActivity : ActivityBase() {
    private lateinit var binding: ActivityMainBinding
    private var REQUEST_CODE_CAMERA = 123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        onGrantAccess()

    }
    private fun onGrantAccess(){
        ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.CAMERA), REQUEST_CODE_CAMERA)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_CODE_CAMERA && grantResults.isNotEmpty() && grantResults[0]  ==  PackageManager.PERMISSION_GRANTED){
//            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            startActivityForResult(intent, REQUEST_CODE_CAMERA)
            replaceFragment(QrCodeFragment())
        }
        else{
            Toast.makeText(this@MainActivity, "Bạn chưa cấp quyền truy cập camera", Toast.LENGTH_SHORT).show()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}










