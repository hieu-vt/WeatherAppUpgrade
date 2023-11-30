package com.example.weatherappupgrade.presentation.modules.profile

import android.Manifest
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.weatherappupgrade.presentation.componets.HeaderScreen.CustomHeaderScreen
import com.example.weatherappupgrade.presentation.modules.weather.model.WeatherViewModel
import com.example.weatherappupgrade.presentation.navigation.DetailProfile

@Composable
fun DetailProfile(navController: NavHostController,  profile: DetailProfile?){
    val weatherViewModel = hiltViewModel<WeatherViewModel>()

    DisposableEffect(Unit) {
        // Launch permissions request when the composable is first composed
        println(weatherViewModel.state)

        // Dispose the launcher when the composable is no longer in the composition
        onDispose {
            // Add any necessary cleanup code here
        }
    }

   Surface {
       CustomHeaderScreen(navController, "Detail Profile")
   }

    Column (modifier = Modifier.fillMaxWidth().zIndex(6f)) {
        Box(modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.Center)) {
            Text("Detail profile of ${profile?.firstName}-${profile?.lastName}")
        }
    }
}

