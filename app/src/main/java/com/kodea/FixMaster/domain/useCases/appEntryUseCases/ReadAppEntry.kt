package com.kodea.FixMaster.domain.useCases.appEntryUseCases

import com.kodea.FixMaster.domain.local.LocalUserManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class readAppEntry @Inject constructor(private val localUserManager: LocalUserManager) {
     operator fun invoke() : Flow<Boolean>{
        return localUserManager.readAppEntry()
    }
}