package com.example.beerinfo.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Malt(
    @SerializedName("amount")
    val amount: AmountX,
    @SerializedName("name")
    val name: String
)