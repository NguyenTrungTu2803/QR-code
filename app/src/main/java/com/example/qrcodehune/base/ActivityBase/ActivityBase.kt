package com.example.qrcodehune.base.ActivityBase

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.qrcodehune.R

open class ActivityBase : AppCompatActivity() {
    fun replaceFragment(fragment: Fragment){
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.flScan, fragment)
            transaction.commit()
        }
    }
}