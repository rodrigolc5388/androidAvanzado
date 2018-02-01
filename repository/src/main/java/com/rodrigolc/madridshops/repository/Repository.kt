package com.rodrigolc.madridshops.repository


interface Repository {
    fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit)
}