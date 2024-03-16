package com.kodea.FixMaster.domain.repository

import androidx.annotation.Keep
import com.kodea.FixMaster.data.remote.dto.geoCodeReverse.geocodeReverse2Response
import com.kodea.FixMaster.data.remote.dto.tomtomApi.rGeocodeResponse

@Keep
interface LocationRepository {
    suspend fun getLoscationInfo(lat : String , lon : String) : geocodeReverse2Response
}