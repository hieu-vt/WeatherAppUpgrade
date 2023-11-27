package com.example.weatherappupgrade.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherappupgrade.presentation.screens.HistoryScreen
import com.example.weatherappupgrade.presentation.screens.HomeScreen
import com.example.weatherappupgrade.presentation.screens.ProfileScreen

@Composable
fun BottomTab() {
    val navController = rememberNavController()
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        MainScreen(navController = navController)
    }
}

