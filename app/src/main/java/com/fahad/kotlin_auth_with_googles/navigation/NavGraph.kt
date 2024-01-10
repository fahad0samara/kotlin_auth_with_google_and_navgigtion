package com.fahad.kotlin_auth_with_googles.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavController

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fahad.kotlin_auth_with_googles.presentation.auth.AuthScreen
import com.fahad.kotlin_auth_with_googles.presentation.auth.AuthViewModel
import com.fahad.kotlin_auth_with_googles.presentation.profile.ProfileScreen

@Composable
fun NavGraph(navController: NavHostController, viewModel: AuthViewModel) {
  NavHost(navController = navController, startDestination = Screen.AuthScreen.route) {
    composable(Screen.AuthScreen.route) {
      AuthScreen(navigateToProfileScreen = { navController.navigate(Screen.ProfileScreen.route) })
    }
    composable(Screen.ProfileScreen.route) {
      ProfileScreen(navController = navController)
    }
  }
}