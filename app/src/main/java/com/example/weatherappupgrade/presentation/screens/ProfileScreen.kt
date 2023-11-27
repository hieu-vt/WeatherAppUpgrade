package com.example.weatherappupgrade.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.weatherappupgrade.presentation.navigation.DetailProfile
import com.example.weatherappupgrade.presentation.navigation.Screen

@Composable
fun ProfileScreen(navController: NavHostController) {
    Surface (modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text("Profile screen")

            Spacer(modifier = Modifier.height(50.dp))

            Column (modifier = Modifier.fillMaxWidth()) {
                val profile = DetailProfile("Hieu", "Vu").toString()

                Button(
                    onClick = {
                        navController.navigate("${Screen.DetailProfile}/$profile")
                    }
                ) {
                    Text("Navigate to Detail profile")
                }
            }
        }

    }
}