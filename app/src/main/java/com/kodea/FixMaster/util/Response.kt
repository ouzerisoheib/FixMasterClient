package com.kodea.FixMaster.util

import com.kodea.FixMaster.data.repository.Category

sealed class Response<out T> {
    object onLoading : Response<Nothing>()
    data class onSuccess<T>(
        val data: T
    ) : Response<T>()

    data class onFaillure<T>(
        val message : String
    ) : Response<T>()
}