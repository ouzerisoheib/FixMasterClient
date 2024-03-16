package com.kodea.FixMaster.presentation.authentification.signUp

import androidx.annotation.Keep
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodea.FixMaster.data.remote.dto.geoCodeReverse.Address
import com.kodea.FixMaster.data.remote.dto.geoCodeReverse.geocodeReverse2Response
import com.kodea.FixMaster.data.remote.dto.tomtomApi.Summary
import com.kodea.FixMaster.data.remote.dto.tomtomApi.rGeocodeResponse
import com.kodea.FixMaster.domain.useCases.locationUseCases.GetLocationUseCases
import com.kodea.FixMaster.util.Constants
import com.kodea.FixMaster.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@Keep
class SignUpViewModel @Inject constructor(
    private val getLocationUseCases: GetLocationUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _data = mutableStateOf<Response<geocodeReverse2Response>>(
        Response.onSuccess(
            geocodeReverse2Response(address = Address("", "", "", "", "", "", "", "", ""),
                emptyList(),"","","","",0,"",0
            )
        )
    )
    var data = _data

    init {
        /*savedStateHandle.get<String>(Constants.latLon)?.let {
            getLocation()
        }
         */
        savedStateHandle.get<String>("lat")?.let {latitude ->
            savedStateHandle.get<String>("lon")?.let {longtitude ->
                getLocation(lat = latitude , lon = longtitude)
            }
        }
        //getLocation("36.576958, 2.904686")
        //getLocation(lat = "36.503397" , "2.876533")
    }

    fun getLocation(lat: String, lon: String) {
        viewModelScope.launch {
            getLocationUseCases(lat, lon).collect {
                _data.value = it
            }
        }
    }

}