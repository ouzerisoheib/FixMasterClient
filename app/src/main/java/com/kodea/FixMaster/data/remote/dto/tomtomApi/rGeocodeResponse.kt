package com.kodea.FixMaster.data.remote.dto.tomtomApi

import com.kodea.FixMaster.domain.model.AddressDataClass

data class rGeocodeResponse(
    val addresses: List<Addresse>,
    val summary: Summary
)

fun rGeocodeResponse.toAddress() : AddressDataClass{
    return AddressDataClass(
        country = addresses.map { it.address.country }.toString(),
        countrySubdivision = addresses.map { it.address.countrySubdivision }.toString(),
        countrySecondarySubdivision = addresses.map { it.address.countrySecondarySubdivision }.toString(),
        postalCode = addresses.map{it.address.postalCode}.toString(),
        freeformAddress = addresses.map { it.address.freeformAddress }.toString()
    )
}