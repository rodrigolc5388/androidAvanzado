package com.rodrigolc.madridshops.repository.cache

import com.rodrigolc.madridshops.repository.model.ShoptivityEntity
import com.rodrigolc.madridshops.utils.SectionType


internal interface Cache {
    fun getAllShoptivities(success: (shoptivities: List<ShoptivityEntity>) -> Unit, error: (errorMessage: String) -> Unit)
    fun getAllShoptivitiesForType(type: SectionType, success: (shoptivities: List<ShoptivityEntity>) -> Unit, error: (errorMessage: String) -> Unit)
    fun saveAllShoptivities(type: String, shoptivities: List<ShoptivityEntity>, success: () -> Unit, error: (errorMessage: String) -> Unit)
    fun deleteAllShoptivities(success: () -> Unit, error: (errorMessage: String) -> Unit)
}
