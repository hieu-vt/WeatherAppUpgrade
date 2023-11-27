package com.example.weatherappupgrade.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.weatherappupgrade.presentation.navigation.BottomTab

class MainActivity : ComponentActivity() {
//    private val viewModel: WeatherViewModel by viewModels()
//    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomTab()
        }
    }
}

