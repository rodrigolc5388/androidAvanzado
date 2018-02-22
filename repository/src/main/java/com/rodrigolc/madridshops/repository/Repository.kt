package com.rodrigolc.madridshops.repository

import com.rodrigolc.madridshops.repository.model.ShoptivityEntity
import com.rodrigolc.madridshops.utils.SectionType


interface Repository {
    fun getAllShoptivities(success: (shoptivities: List<ShoptivityEntity>) -> Unit, error: (errorMessage: String) -> Unit)
    fun getAllShoptivitiesForType(type: SectionType, success: (shoptivities: List<ShoptivityEntity>) -> Unit, error: (errorMessage: String) -> Unit)
    fun deleteAllShoptivities(success: () -> Unit, error: (errorMessage: String) -> Unit)

}