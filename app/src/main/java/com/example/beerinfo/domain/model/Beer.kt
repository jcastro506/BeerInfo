package com.example.beerinfo.domain.model

import com.example.beerinfo.data.remote.dto.Ingredients
import com.example.beerinfo.data.remote.dto.Method
import com.google.gson.annotations.SerializedName

data class Beer(
    val description: String,
    @SerializedName("food_pairing")
    val foodPairing: List<String>,
    @SerializedName("ibu")
    val ibu: Double,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("ingredients")
    val ingredients: Ingredients,
    @SerializedName("name")
    val name: String,
)
