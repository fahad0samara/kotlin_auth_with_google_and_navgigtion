package com.fahad.kotlin_auth_with_googles.ui.screen.Home

import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.fahad.myapplication.ui.screen.profile.UserDataViewModel
import androidx.compose.runtime.remember

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

import com.fahad.myapplication.ui.Utils.image.AsyncImageProfile

@Composable
fun Home(
  navController: NavHostController,
  userDataViewModel: UserDataViewModel
) {
  // Observe the user data from the view model
  val displayNameState = remember { userDataViewModel.displayName }
  val photoUrlState = remember { userDataViewModel.photoUrl }

  // Sample list of sports events

  // LazyColumn for displaying user information and sports content
  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp)
  ) {
    // Display user's profile image using Coil
    item {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        // Display user's profile image on the right
        AsyncImageProfile(photoUrl = photoUrlState,)

        Spacer(modifier = Modifier.width(16.dp))

        // Display user's name on the left
        Text(
          text = "Welcome, $displayNameState!",
          fontSize = 24.sp,
          fontWeight = FontWeight.Bold,
          textAlign = TextAlign.Start
        )
      }
    }

    // Search Bar (You can replace this with your custom search bar implementation)
    item {
      OutlinedTextField(
        value = "",
        onValueChange = { /* Handle search query change */ },
        label = { Text("Search for sports events") },
        modifier = Modifier
          .fillMaxWidth()
          .padding(8.dp)
      )
    }



    // Display text lager text for
    item {
      Text(
        text = "these app is for the sign in with google and navigation component and also the compose ui",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier
          .fillMaxWidth()
          .padding(8.dp),
        letterSpacing = 0.15.sp

      )
    }

  }
}








