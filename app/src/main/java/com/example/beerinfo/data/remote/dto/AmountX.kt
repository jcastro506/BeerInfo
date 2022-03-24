package com.example.beerinfo.data.remote.dto


import com.google.gson.annotations.SerializedName

data class AmountX(
    @SerializedName("unit")
    val unit: String,
    @SerializedName("value")
    val value: Double
)