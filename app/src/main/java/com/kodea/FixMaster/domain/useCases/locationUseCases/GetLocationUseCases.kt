package com.kodea.FixMaster.domain.useCases.locationUseCases

import androidx.annotation.Keep
import com.kodea.FixMaster.data.remote.dto.geoCodeReverse.geocodeReverse2Response
import com.kodea.FixMaster.data.remote.dto.tomtomApi.rGeocodeResponse
import com.kodea.FixMaster.domain.repository.LocationRepository
import com.kodea.FixMaster.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@Keep
class GetLocationUseCases @Inject constructor(private  val locationRepository: LocationRepository) {
    operator fun invoke(lat : String , lon : String) : Flow<Response<geocodeReverse2Response>> = flow {
        try {
            emit(Response.onLoading)
            val data = locationRepository.getLoscationInfo(lat, lon)
            emit(Response.onSuccess(data))
        }catch (e: HttpException){
            emit(Response.onFaillure(e.localizedMessage?: "Unexpected error"))
        }catch (e: IOException){
            emit(Response.onFaillure("Couldn't reach server check your internet"))
        }
    }
}