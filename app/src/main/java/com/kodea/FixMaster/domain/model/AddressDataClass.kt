package com.kodea.FixMaster.domain.model

data class AddressDataClass(
    val country: String,
    val countrySubdivision: String,
    val countrySecondarySubdivision: String,
    val postalCode: String,
    val freeformAddress: String
)