package com.example.smartparking.retrofit

import android.util.Log
import com.example.smartparking.response.ParkingLotsResponse
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

    class ApiRequest {

        var STAGING_BASE_URL = "http://127.0.0.1:5000/"

    var builder = OkHttpClient().newBuilder()
        .readTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(5, TimeUnit.SECONDS)
        .addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        ).build()

    var retrofit : Retrofit = Retrofit.Builder().baseUrl(STAGING_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(builder)
        .build()


    var service: ApiInterface = retrofit.create(ApiInterface::class.java)

    fun getParkingLots () {
        var responseCall : Call<ParkingLotsResponse> = service.getParkingLots()
        responseCall.enqueue(object :Callback<ParkingLotsResponse>
        {
            override fun onResponse(
                call: Call<ParkingLotsResponse>,
                response: Response<ParkingLotsResponse>
            )
            {
                if (response.isSuccessful){
                    if (response.body()?.resultType ==0){
                        Log.i("TAG", "onResponse: "+ Gson().toJson(response.body()!!.parkingSpotData))
                    }
                }
            }
            override fun onFailure(call: Call<ParkingLotsResponse>, t: Throwable) {

            }
        }
        )

    }


}