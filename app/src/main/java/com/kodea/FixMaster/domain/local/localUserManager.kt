package com.kodea.FixMaster.domain.local

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
    suspend fun saveAppEntry()
    fun readAppEntry() : Flow<Boolean>
}