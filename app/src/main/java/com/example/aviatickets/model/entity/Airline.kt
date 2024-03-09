package com.example.aviatickets.model.entity

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Airline(
    @SerializedName("name") val name: String,
    @SerializedName("code") val code: String
)