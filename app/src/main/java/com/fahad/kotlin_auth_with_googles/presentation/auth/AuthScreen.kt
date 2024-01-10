package com.fahad.kotlin_auth_with_googles.presentation.auth

import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fahad.kotlin_auth_with_googles.core.Constants.AUTH_SCREEN
import com.fahad.kotlin_auth_with_googles.domain.model.Response
import com.fahad.kotlin_auth_with_googles.presentation.auth.components.SignInButton
import com.fahad.kotlin_auth_with_googles.ui.Utils.ProgressBar
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider.getCredential

@Composable
fun AuthScreen(
  viewModel: AuthViewModel = hiltViewModel(),
  navigateToProfileScreen: () -> Unit
) {
  val launcher = rememberLauncherForActivityResult(StartIntentSenderForResult()) { result ->
    if (result.resultCode == RESULT_OK) {
      try {
        val credentials = viewModel.oneTapClient.getSignInCredentialFromIntent(result.data)
        val googleIdToken = credentials.googleIdToken
        val googleCredentials = getCredential(googleIdToken, null)
        viewModel.signInWithGoogle(googleCredentials)
      } catch (it: ApiException) {
        print(it)
      }
    }
  }

  fun launch(signInResult: BeginSignInResult) {
    val intent = IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
    launcher.launch(intent)
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // AuthTopBar
    Text(
      text = AUTH_SCREEN,
      fontSize = 24.sp,
      fontWeight = FontWeight.Bold
    )

    // Spacer
    Spacer(modifier = Modifier.height(16.dp))

    // AuthContent
    SignInButton(
      onClick = {
        viewModel.oneTapSignIn()
      }
    )

    // Spacer
    Spacer(modifier = Modifier.height(16.dp))

    // OneTapSignIn
    when (val oneTapSignInResponse = viewModel.oneTapSignInResponse) {
      is Response.Loading -> ProgressBar()
      is Response.Success -> oneTapSignInResponse.data?.let {
        LaunchedEffect(it) {
          launch(it)
        }
      }
      is Response.Failure -> LaunchedEffect(Unit) {
        print(oneTapSignInResponse.e)
      }
    }

    // Spacer
    Spacer(modifier = Modifier.height(8.dp))

    // SignInWithGoogle
    when (val signInWithGoogleResponse = viewModel.signInWithGoogleResponse) {
      is Response.Loading -> ProgressBar()
      is Response.Success -> signInWithGoogleResponse.data?.let { signedIn ->
        LaunchedEffect(signedIn) {
          if (signedIn) {
            navigateToProfileScreen()
          }
        }
      }
      is Response.Failure -> LaunchedEffect(Unit) {
        print(signInWithGoogleResponse.e)
      }
    }
  }
}

