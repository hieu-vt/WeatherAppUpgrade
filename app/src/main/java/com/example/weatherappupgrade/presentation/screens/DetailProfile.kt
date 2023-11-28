package com.example.weatherappupgrade.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.weatherappupgrade.presentation.componets.HeaderScreen.CustomHeaderScreen
import com.example.weatherappupgrade.presentation.navigation.DetailProfile

@Composable
fun DetailProfile(navController: NavHostController,  profile: DetailProfile?){
   Surface {
       CustomHeaderScreen(navController, "Detail Profile")
   }

    Column (modifier = Modifier.fillMaxWidth().zIndex(6f)) {
        Box(modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.Center)) {
            Text("Detail profile of ${profile?.firstName}-${profile?.lastName}")
        }
    }
}

