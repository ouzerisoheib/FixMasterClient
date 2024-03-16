package com.kodea.FixMaster.domain.useCases.authentifaction

import com.kodea.FixMaster.domain.repository.AuthRepository
import javax.inject.Inject

class getUserAuthState @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke() = repository.getUserAuthState()
}