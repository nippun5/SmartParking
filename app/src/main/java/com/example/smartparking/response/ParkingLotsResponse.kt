package com.example.smartparking.response

import com.google.gson.annotations.SerializedName

data class ParkingLotsResponse(

	@field:SerializedName("parkingSpotData")
	val parkingSpotData: List<ParkingSpotDataItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("resultType")
	val resultType: Int? = null
)

data class ParkingSpotDataItem(

	@field:SerializedName("availableSpots")
	val availableSpots: Int? = null,

	@field:SerializedName("parkingImage")
	val parkingImage: String? = null,

	@field:SerializedName("parkingSpots")
	val parkingSpots: Int? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("parkingName")
	val parkingName: String? = null
)
