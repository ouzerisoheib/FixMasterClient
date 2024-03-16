package com.kodea.FixMaster.domain.repository

import com.kodea.FixMaster.util.Response
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
     fun signInWithEmailandPassword(email : String , password : String) : Flow<Response<Boolean>>
     fun signUp(email: String , password: String , userName : String) : Flow<Response<Boolean>>
     fun signOut() : Flow<Response<Boolean>>
     fun isUserAuth() : Boolean
     fun getUserAuthState() : Flow<Boolean>
}