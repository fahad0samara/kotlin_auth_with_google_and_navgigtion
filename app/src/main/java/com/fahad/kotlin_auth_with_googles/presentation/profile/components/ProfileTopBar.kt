package ro.alexmamo.firebasesigninwithgoogle.presentation.profile.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import com.fahad.kotlin_auth_with_googles.core.Constants.PROFILE_SCREEN
import com.fahad.kotlin_auth_with_googles.core.Constants.REVOKE_ACCESS
import com.fahad.kotlin_auth_with_googles.core.Constants.SIGN_OUT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopBar(
    signOut: () -> Unit,
    revokeAccess: () -> Unit
) {
    var openMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(
                text = PROFILE_SCREEN
            )
        },
        actions = {
          IconButton(
            onClick = {
              openMenu = !openMenu
            }
          ) {
            Icon(
              imageVector = Icons.Outlined.MoreVert,
              contentDescription = null,
            )
          }
          DropdownMenu(
            expanded = openMenu,
            onDismissRequest = {
              openMenu = !openMenu
            }
          ) {

          }
        }
    )
}



