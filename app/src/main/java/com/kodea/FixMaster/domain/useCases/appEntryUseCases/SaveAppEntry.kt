package com.kodea.FixMaster.domain.useCases.appEntryUseCases

import com.kodea.FixMaster.domain.local.LocalUserManager
import javax.inject.Inject

class saveAppEntry @Inject constructor(private val localUserManager: LocalUserManager) {
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}