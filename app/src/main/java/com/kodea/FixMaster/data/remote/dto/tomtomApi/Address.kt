package com.kodea.FixMaster.data.remote.dto.tomtomApi

import com.google.gson.annotations.SerializedName

data class Address(
    val boundingBox: BoundingBox,
    val country: String,
    @SerializedName(value = "c")
    val countryCode: String,
    val countryCodeISO3: String,
    val countrySecondarySubdivision: String,
    val countrySubdivision: String,
    val countrySubdivisionCode: String,
    val countrySubdivisionName: String,
    val freeformAddress: String,
    val localName: String,
    val municipality: String,
    val postalCode: String,
    val routeNumbers: List<String>
)