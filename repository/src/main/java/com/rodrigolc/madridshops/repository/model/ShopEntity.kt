package com.rodrigolc.madridshops.repository.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
internal data class  ShopEntity(
        val id: Long,
        val databaseId: Long,
        val name: String,
        @JsonProperty("description_en") val description: String,
        @JsonProperty("gps_lat") val latitude: Float,
        @JsonProperty("gps_lon") val longitude: Float,
        val img: String,
        @JsonProperty("logo_img") val logo: String,
        @JsonProperty("opening_hours_en") val openingHours: String,
        val address: String
)