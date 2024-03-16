package com.kodea.FixMaster.data.remote.dto.geoCodeReverse

import androidx.annotation.Keep

@Keep
data class geocodeReverse2Response(
    val address: Address,
    val boundingbox: List<String>,
    val display_name: String,
    val lat: String,
    val licence: String,
    val lon: String,
    val osm_id: Int,
    val osm_type: String,
    val place_id: Int
)