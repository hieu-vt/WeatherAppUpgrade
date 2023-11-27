package com.example.weatherappupgrade.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weatherappupgrade.presentation.ui.theme.Purple40
import com.example.weatherappupgrade.presentation.ui.theme.WeatherAppUpgradeTheme
import kotlinx.coroutines.launch
import com.example.weatherappupgrade.presentation.componets.CustomTab
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

