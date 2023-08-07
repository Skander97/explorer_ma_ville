package com.example.explorermaville

data class FoursquareResponse(
    val results: List<FoursquareVenue>
)

data class FoursquareVenue(
    val fsq_id: String,
    val name: String,
    val location: FoursquareLocation,
    val categories: List<FoursquareCategory>,
    val distance: Int,
)

data class FoursquareLocation(
    val address: String?,
    val latitude: Double,
    val longitude: Double,
    val formatted_address: String?,
    val locality: String?,
    val region: String?,
)

data class FoursquareCategory(
    val id: Int,
    val name: String,
    val icon: FoursquareIcon
)

data class FoursquareIcon(
    val prefix: String,
    val suffix: String
)