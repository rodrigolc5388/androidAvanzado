package com.rodrigolc.madridshops.repository.cache

import com.rodrigolc.madridshops.repository.model.ShoptivityEntity


internal interface Cache {
    fun getAllShoptivities(success: (shoptivities: List<ShoptivityEntity>) -> Unit, error: (errorMessage: String) -> Unit)
    fun saveAllShoptivities(shoptivities: List<ShoptivityEntity>, success: () -> Unit, error: (errorMessage: String) -> Unit)
    fun deleteAllShoptivities(success: () -> Unit, error: (errorMessage: String) -> Unit)
}
