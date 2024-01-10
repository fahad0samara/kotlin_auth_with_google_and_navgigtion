package com.fahad.kotlin_auth_with_googles.presentation.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.fahad.kotlin_auth_with_googles.presentation.auth.components.SignInButton
import com.fahad.kotlin_auth_with_googles.presentation.profile.ProfileViewModel

@Composable
fun ProfileContent(
  viewModel: ProfileViewModel = hiltViewModel(),
  padding: PaddingValues,
  photoUrl: String,
  displayName: String,

) {
  Column(
    Modifier.fillMaxSize().padding(padding), horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Spacer(
      modifier = Modifier.height(48.dp)
    )
    AsyncImage(
      model = ImageRequest.Builder(LocalContext.current)
        .data(photoUrl)
        .crossfade(true)
        .build(),
      contentDescription = null,
      contentScale = Crop,
      modifier = Modifier.clip(CircleShape).width(96.dp).height(96.dp)
    )
    Text(
      text = displayName,
      fontSize = 24.sp
    )
  }

}
