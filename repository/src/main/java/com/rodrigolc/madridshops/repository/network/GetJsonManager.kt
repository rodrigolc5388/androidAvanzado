package com.rodrigolc.madridshops.repository.network

import com.rodrigolc.madridshops.repository.ErrorCompletion
import com.rodrigolc.madridshops.repository.SuccessCompletion

interface GetJsonManager {
    fun execute(url: String, success: SuccessCompletion<String>, error: ErrorCompletion)
}
