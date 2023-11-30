package com.example.weatherappupgrade.presentation.modules.weather

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.weatherappupgrade.presentation.componets.HeaderScreen.CustomHeaderScreen
import com.example.weatherappupgrade.presentation.modules.weather.components.WeatherCard
import com.example.weatherappupgrade.presentation.modules.weather.model.WeatherViewModel
@Composable
fun WeatherScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<WeatherViewModel>()
    // Use rememberSaveable to retain the launcher across recompositions
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        if (it.values.all { granted -> granted }) {
            // Permissions granted, load weather info
            viewModel.loadWeatherInfo()
        } else {
            // Handle the case where permissions are not granted
            // You might want to show a message or take appropriate action
        }
    }

    DisposableEffect(Unit) {
        // Launch permissions request when the composable is first composed
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

        // Dispose the launcher when the composable is no longer in the composition
        onDispose {
            // Add any necessary cleanup code here
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
        ) {
            CustomHeaderScreen(navController = navController, title = "Weather screen")

            WeatherCard(
                state = viewModel.state,
                backgroundColor = Color.Blue
            )
            Spacer(modifier = Modifier.height(16.dp))
//                  WeatherForecast(state = viewModel.state)
        }

        if(viewModel.state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        viewModel.state.error?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

}