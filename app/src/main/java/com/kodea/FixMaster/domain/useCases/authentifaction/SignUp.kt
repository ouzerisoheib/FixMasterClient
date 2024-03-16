package com.kodea.FixMaster.domain.useCases.authentifaction

import com.kodea.FixMaster.domain.repository.AuthRepository
import javax.inject.Inject

class signUp @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(email : String , password : String , userName : String) = repository.signUp(email , password , userName)
}