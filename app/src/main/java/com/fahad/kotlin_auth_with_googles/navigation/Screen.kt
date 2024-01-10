package com.fahad.kotlin_auth_with_googles.navigation

import com.fahad.kotlin_auth_with_googles.core.Constants.AUTH_SCREEN
import com.fahad.kotlin_auth_with_googles.core.Constants.PROFILE_SCREEN

sealed class Screen(val route: String) {
    data object AuthScreen: Screen(AUTH_SCREEN)
    data object ProfileScreen: Screen(PROFILE_SCREEN)
}