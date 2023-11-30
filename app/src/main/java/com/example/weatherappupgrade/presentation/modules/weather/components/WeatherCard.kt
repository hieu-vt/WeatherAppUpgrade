package com.example.weatherappupgrade.presentation.modules.weather.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherappupgrade.R
import com.example.weatherappupgrade.presentation.modules.weather.state.WeatherState
import java.time.format.DateTimeFormatter

@Composable
fun WeatherCard(
    state: WeatherState,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    state.weatherInfo?.CurrentWeatherData?.let {
        Card (
            modifier = modifier.padding(16.dp).background(backgroundColor),
            shape = RoundedCornerShape(10.dp),
        ) {
            Column (modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Today ${it.time.format(DateTimeFormatter.ofPattern("HH:mm"))}",
                    modifier = Modifier.align(alignment = Alignment.End),
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(16.dp))

                Image(
                    painter = painterResource(id = it.weatherType.iconRes),
                    contentDescription = it.weatherType.weatherDesc,
                    modifier = Modifier.width(200.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "${it.temperatureCelsius} *C",
                    fontSize = 50.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = it.weatherType.weatherDesc,
                    color = Color.White,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(32.dp))

                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                    WeatherDataDisplay(
                        value = it.windSpeed,
                        unit = "Hpa",
                        icon = ImageVector.vectorResource(id =  R.drawable.ic_pressure)
                    )

                    WeatherDataDisplay(
                        value = it.windSpeed,
                        unit = "%",
                        icon = ImageVector.vectorResource(id =  R.drawable.ic_snowy)
                    )

                    WeatherDataDisplay(
                        value = it.windSpeed,
                        unit = "Kg",
                        icon = ImageVector.vectorResource(id =  R.drawable.ic_rainy)
                    )
                }
            }
        }
    }
}
