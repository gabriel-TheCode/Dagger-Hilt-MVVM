package com.thecode.dagger_hilt_mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.thecode.dagger_hilt_mvvm.R

class MainActivity : AppCompatActivity() {
    private val TAG: String = "AppDebug"
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


}