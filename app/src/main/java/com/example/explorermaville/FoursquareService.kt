package com.example.explorermaville

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface FoursquareService {
    @Headers("Authorization: fsq3ls6swIOOhtW3wu+jrEwXij7FWSH1BTS8drc2FSImVAY=")
    @GET("places/search")
    fun getNearbyPlaces(
        @Query("ll") latLng: String,
        @Query("radius") radius: Int
    ): Call<FoursquareResponse>
}