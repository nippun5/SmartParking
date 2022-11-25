package com.example.smartparking.retrofit

import com.example.smartparking.response.ParkingLotsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("getParkingLots")
    fun getParkingLots() : Call<ParkingLotsResponse>
}