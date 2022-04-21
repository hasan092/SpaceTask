package com.example.spacetask.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.spacetask.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}