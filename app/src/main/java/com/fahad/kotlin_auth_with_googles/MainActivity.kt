package com.fahad.kotlin_auth_with_googles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.fahad.kotlin_auth_with_googles.ui.theme.KotlinauthwithgooglesTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.fahad.kotlin_auth_with_googles.navigation.NavGraph
import com.fahad.kotlin_auth_with_googles.navigation.Screen
import com.fahad.kotlin_auth_with_googles.presentation.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

// Screen.kt


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  private lateinit var navController: NavHostController
  private val viewModel by viewModels<AuthViewModel>()

  @OptIn(ExperimentalAnimationApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      KotlinauthwithgooglesTheme {
        // A surface container using the 'background' color from the theme
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colorScheme.background
        ) {
          navController = rememberAnimatedNavController()
          NavGraph(
            navController = navController,
            viewModel = viewModel
          )

          // Observe changes in authentication state
          LaunchedEffect(viewModel.isUserAuthenticated) {
            if (viewModel.isUserAuthenticated) {
              navController.navigate(Screen.ProfileScreen.route)
            } else {
              navController.navigate(Screen.AuthScreen.route)
            }
          }
        }
      }
    }
  }
}










