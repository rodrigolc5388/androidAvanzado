package com.rodrigolc.madridshops.repository.cache


internal interface Cache {
    fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit)
}
