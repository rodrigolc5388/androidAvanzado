package com.rodrigolc.madridshops.domain.Interactor.internetStatus

import com.rodrigolc.madridshops.domain.Interactor.ErrorClosure


interface InternetStatusInteractor {
    fun execute(success: () -> Unit, error: ErrorClosure)
}
