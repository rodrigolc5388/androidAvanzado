package com.rodrigolc.madridshops.repository

import com.rodrigolc.madridshops.repository.model.ShoptivityEntity


interface Repository {
    fun getAllShops(success: (shoptivities: List<ShoptivityEntity>) -> Unit, error: (errorMessage: String) -> Unit)
    fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit)

}