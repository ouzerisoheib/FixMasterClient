package com.kodea.FixMaster.domain.useCases

import com.kodea.FixMaster.domain.repository.FireStoreRepository
import javax.inject.Inject


class getCategoriesUseCase @Inject constructor (private val repository: FireStoreRepository){
    operator fun invoke() = repository.getCategories()

}