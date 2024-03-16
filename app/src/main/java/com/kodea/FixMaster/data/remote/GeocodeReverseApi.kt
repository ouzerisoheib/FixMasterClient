package com.kodea.FixMaster.data.remote

import com.kodea.FixMaster.data.remote.dto.tomtomApi.rGeocodeResponse
import com.kodea.FixMaster.util.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GeocodeReverseApi {
    @GET("search/2/reverseGeocode/{position}.json")
    suspend fun getLocactionInfo(
        @Path("position") position : String ,
        @Query("key") key : String = Constants.apiKey
    ) : rGeocodeResponse
}