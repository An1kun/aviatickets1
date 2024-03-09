package com.example.aviatickets.model.entity

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class Location(
    @SerializedName("city_name") val cityName: String,
    @SerializedName("code") val code: String
)