package com.example.weatherappupgrade.presentation.modules.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HistoryScreen(){
    Surface {
        Box(modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.Center))
        Text("History screen")
    }
}