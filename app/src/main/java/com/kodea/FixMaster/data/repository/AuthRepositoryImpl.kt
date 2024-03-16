package com.kodea.FixMaster.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kodea.FixMaster.domain.repository.AuthRepository
import com.kodea.FixMaster.util.Response
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    val auth: FirebaseAuth,
    val firestore: FirebaseFirestore
) : AuthRepository {


    var operationSuccessful: Boolean = false

    override fun signInWithEmailandPassword(
        email: String,
        password: String
    ): Flow<Response<Boolean>> = flow {
        operationSuccessful = false
        try {
            emit(Response.onLoading)
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                operationSuccessful = true
            }.await()
            emit(Response.onSuccess(operationSuccessful))

        } catch (e: Exception) {
            emit(Response.onFaillure(e.localizedMessage ?: "An enexpected error"))
        }
    }

    override fun signUp(
        email: String,
        password: String,
        userName: String
    ): Flow<Response<Boolean>> = flow {
        operationSuccessful = false
        try {
            emit(Response.onLoading)
            auth.createUserWithEmailAndPassword(email , password).addOnSuccessListener {
                operationSuccessful = true
                //TODO add user to fireStore
            }.await()
            emit(Response.onSuccess(operationSuccessful))
        }catch (e : Exception){
            emit(Response.onFaillure(e.localizedMessage?:"An enexpected error"))
        }
    }

    override fun signOut(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.onLoading)
            auth.signOut()
            emit(Response.onSuccess(true))

        } catch (e: Exception) {
            emit(Response.onFaillure(e.localizedMessage ?: "An enexpected error"))
        }
    }

    override fun isUserAuth(): Boolean {
        return auth.currentUser != null
    }

    @ExperimentalCoroutinesApi
    override fun getUserAuthState(): Flow<Boolean> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener {
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }
}