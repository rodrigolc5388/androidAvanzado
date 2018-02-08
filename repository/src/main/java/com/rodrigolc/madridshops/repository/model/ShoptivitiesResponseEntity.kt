package com.rodrigolc.madridshops.repository.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties


@JsonIgnoreProperties(ignoreUnknown = true)
internal class ShoptivitiesResponseEntity(
        val result: List<ShoptivityEntity>
)
