package com.example.beerinfo.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Fermentation(
    @SerializedName("temp")
    val temp: Temp
)