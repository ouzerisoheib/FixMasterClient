package com.kodea.FixMaster.data.remote

import androidx.annotation.Keep
import com.kodea.FixMaster.data.remote.dto.geoCodeReverse.geocodeReverse2Response
import retrofit2.http.GET
import retrofit2.http.Query


interface GeocodeReverseApi2 {
    @GET("reverse")
    suspend fun getLocationInfo(
        @Query("lat") lat : String,
        @Query("lon") lon : String,
        @Query("api_key") api_key : String = "65da298808052629177612scj12fc88",
    ): geocodeReverse2Response
}

//https://geocode.maps.co/reverse?lat=36.503397&lon=2.876533&api_key=65da298808052629177612scj12fc88