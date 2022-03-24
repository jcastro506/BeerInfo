package com.example.beerinfo.data.remote.dto


import com.google.gson.annotations.SerializedName

data class BoilVolume(
    @SerializedName("unit")
    val unit: String,
    @SerializedName("value")
    val value: Int
)