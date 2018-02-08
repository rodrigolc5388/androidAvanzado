package com.rodrigolc.madridshops.repository.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class  ShoptivityEntity(
        val id: Long,
        val databaseId: Long,
        val name: String,
        val address: String,
        val description_en: String,
        val description_es: String,
        val img: String,
        @JsonProperty("logo_img") val logo: String,
        @JsonProperty("opening_hours_en") val openingHoursEn: String,
        @JsonProperty("opening_hours_es") val openingHoursEs: String,
        @JsonProperty("gps_lat") val latitude: String,
        @JsonProperty("gps_lon") val longitude: String
)
