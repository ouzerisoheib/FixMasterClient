package com.kodea.FixMaster.data.remote.dto.geoCodeReverse


import com.google.gson.annotations.SerializedName

@androidx.annotation.Keep
data class Address(
    @SerializedName(value = "ISO3166-2-lvl4")
    val ISO3166_2_lvl4: String,
    val amenity: String,
    val country: String,
    val country_code: String,
    val county: String,
    val postcode: String,
    val road: String,
    val state: String,
    val town: String
)