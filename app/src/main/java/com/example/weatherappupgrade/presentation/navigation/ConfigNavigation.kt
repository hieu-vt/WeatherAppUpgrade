package com.example.weatherappupgrade.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.weatherappupgrade.presentation.screens.DetailProfile
import com.example.weatherappupgrade.presentation.screens.HistoryScreen
import com.example.weatherappupgrade.presentation.screens.HomeScreen
import com.example.weatherappupgrade.presentation.screens.ProfileScreen
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

        composable("${NavigationItem.DetailProfile.route}/{profile}",
            arguments = listOf(navArgument(NavigationItem.DetailProfile.route){
                type = NavType.StringType
            })
        ) { navBackStackEntry->
            val profile = navBackStackEntry.arguments?.getString(NavigationItem.DetailProfile.route)?.let {
                Gson().fromJson(it, DetailProfile::class.java)
            }

            DetailProfile(profile)
        }
    }
}

