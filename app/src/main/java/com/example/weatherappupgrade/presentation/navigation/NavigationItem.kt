package com.example.weatherappupgrade.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.List
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen {
    HOME,
    History,
    Profile,
    DetailProfile,
    Weather
}


sealed class BarNavigationItem(var route: String, val icon: ImageVector?, var title: String) {
    object Home : BarNavigationItem(Screen.HOME.toString(), Icons.Rounded.Home, "Home")
    object History : BarNavigationItem(Screen.History.toString(), Icons.Rounded.List, "History")
    object Profile : BarNavigationItem(Screen.Profile.toString(), Icons.Rounded.Info, "Profile")
}

sealed class NavigationItem(var route: String) {
    object DetailProfile : NavigationItem("${Screen.DetailProfile}/{profile}")

    object Weather : NavigationItem(Screen.Weather.toString())
}

