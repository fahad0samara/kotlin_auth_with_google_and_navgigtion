package com.fahad.kotlin_auth_with_googles.presentation.auth.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.fahad.kotlin_auth_with_googles.ui.Utils.ProgressBar
import com.fahad.kotlin_auth_with_googles.core.Utils.Companion.print
import com.fahad.kotlin_auth_with_googles.domain.model.Response.*
import com.fahad.kotlin_auth_with_googles.presentation.auth.AuthViewModel

@Composable
fun OneTapSignIn(
  viewModel: AuthViewModel = hiltViewModel(),
  launch: (result: BeginSignInResult) -> Unit
) {
    when(val oneTapSignInResponse = viewModel.oneTapSignInResponse) {
        is Loading -> ProgressBar()
        is Success -> oneTapSignInResponse.data?.let {
            LaunchedEffect(it) {
                launch(it)
            }
        }
        is Failure -> LaunchedEffect(Unit) {
            print(oneTapSignInResponse.e)
        }
    }
}