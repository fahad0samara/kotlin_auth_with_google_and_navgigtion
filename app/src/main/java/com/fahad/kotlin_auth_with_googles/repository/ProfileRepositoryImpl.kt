package com.fahad.kotlin_auth_with_googles.repository

import com.fahad.kotlin_auth_with_googles.domain.model.Response
import com.fahad.kotlin_auth_with_googles.domain.repository.ProfileRepository
import com.fahad.kotlin_auth_with_googles.domain.repository.RevokeAccessResponse
import com.fahad.kotlin_auth_with_googles.domain.repository.SignOutResponse
import com.fahad.kotlin_auth_with_googles.ui.Constants.USERS
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImpl @Inject constructor(
  private val auth: FirebaseAuth,
  private var oneTapClient: SignInClient,
  private var signInClient: GoogleSignInClient,
  private val db: FirebaseFirestore, override val email: String
) : ProfileRepository {
    override val displayName = auth.currentUser?.displayName.toString()
    override val photoUrl = auth.currentUser?.photoUrl.toString()

    override suspend fun signOut(): SignOutResponse {
        return try {
            oneTapClient.signOut().await()
            auth.signOut()
          Response.Success(true)
        } catch (e: Exception) {
          Response.Failure(e)
        }
    }

    override suspend fun revokeAccess(): RevokeAccessResponse {
        return try {
            auth.currentUser?.apply {
                db.collection(USERS).document(uid).delete().await()
                signInClient.revokeAccess().await()
                oneTapClient.signOut().await()
                delete().await()
            }
          Response.Success(true)
        } catch (e: Exception) {
          Response.Failure(e)
        }
    }
}