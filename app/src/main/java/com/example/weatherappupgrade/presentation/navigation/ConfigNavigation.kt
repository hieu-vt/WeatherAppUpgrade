package com.example.weatherappupgrade.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.weatherappupgrade.presentation.modules.profile.DetailProfile
import com.example.weatherappupgrade.presentation.modules.history.HistoryScreen
import com.example.weatherappupgrade.presentation.modules.home.HomeScreen
import com.example.weatherappupgrade.presentation.modules.profile.ProfileScreen
import com.google.gson.Gson

@Composable
fun Navigations(navController: NavHostController) {
    NavHost(navController, startDestination = BarNavigationItem.Home.route) {
        composable(BarNavigationItem.Home.route) {
            HomeScreen()
        }
        composable(BarNavigationItem.History.route) {
            HistoryScreen()
        }
        composable(BarNavigationItem.Profile.route) {
            ProfileScreen(navController)
        }

        composable(NavigationItem.DetailProfile.route,
            arguments = listOf(navArgument("profile"){
                type = NavType.StringType
            })
        ) { navBackStackEntry->
            val profile = navBackStackEntry.arguments?.getString("profile")?.let {
                Gson().fromJson(it, DetailProfile::class.java)
            }

            DetailProfile(navController, profile)
        }
    }
}

