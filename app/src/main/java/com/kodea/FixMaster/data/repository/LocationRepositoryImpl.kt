package com.kodea.FixMaster.data.repository

import androidx.annotation.Keep
import com.kodea.FixMaster.data.remote.GeocodeReverseApi
import com.kodea.FixMaster.data.remote.GeocodeReverseApi2
import com.kodea.FixMaster.data.remote.dto.geoCodeReverse.geocodeReverse2Response
import com.kodea.FixMaster.data.remote.dto.tomtomApi.rGeocodeResponse
import com.kodea.FixMaster.domain.repository.LocationRepository
import javax.inject.Inject


class LocationRepositoryImpl @Inject constructor (private val api : GeocodeReverseApi2) : LocationRepository {
    override suspend fun getLoscationInfo(lat : String , lon : String) : geocodeReverse2Response {
        return (api.getLocationInfo(lat, lon))
    }
}