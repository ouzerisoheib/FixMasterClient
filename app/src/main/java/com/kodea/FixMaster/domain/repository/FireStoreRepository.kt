package com.kodea.FixMaster.domain.repository

import com.kodea.FixMaster.data.repository.Category
import com.kodea.FixMaster.util.Response
import kotlinx.coroutines.flow.Flow

interface FireStoreRepository {
    // fun getCategories() : Flow<Response<Category>>
     fun getCategories() : Flow<Response<List<Category>>>
     fun getLimitCategories() : Flow<Response<List<Category>>>
}