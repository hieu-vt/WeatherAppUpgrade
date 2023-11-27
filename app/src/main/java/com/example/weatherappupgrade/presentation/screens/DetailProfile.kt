package com.example.weatherappupgrade.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.weatherappupgrade.presentation.navigation.DetailProfile

@Composable
fun DetailProfile(profile: DetailProfile?){
    Surface {
        Box(modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.Center))
        Text("Detail profile of ${profile?.firstName} ${profile?.lastName}")
    }
}