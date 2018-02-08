package com.rodrigolc.madridshops.repository.cache

import com.rodrigolc.madridshops.repository.model.ShoptivityEntity


internal interface Cache {
    fun getAllShops(success: (shoptivities: List<ShoptivityEntity>) -> Unit, error: (errorMessage: String) -> Unit)
    fun saveAllShops(shoptivities: List<ShoptivityEntity>, success: () -> Unit, error: (errorMessage: String) -> Unit)
    fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit)
}
