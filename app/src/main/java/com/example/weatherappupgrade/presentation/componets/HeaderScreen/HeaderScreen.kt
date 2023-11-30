package com.example.weatherappupgrade.presentation.componets.HeaderScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.weatherappupgrade.R

@Composable
fun CustomHeaderScreen (navController: NavHostController, title : String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().height(50.dp).background(Color.White)
    ) {
        Box (modifier = Modifier.fillMaxHeight()){

            Box (modifier = Modifier
                .fillMaxHeight()
                .wrapContentSize(align = Alignment.Center)
                .padding(paddingValues = PaddingValues(16.dp))
                .clickable (
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    indication = null,
                ) {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.arrow_left),
                    contentDescription = "Arrow left",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(16.dp)
                )
            }

            Box(modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.Center)){
                Text(title, fontSize = 14.sp)
            }

        }
    }
}