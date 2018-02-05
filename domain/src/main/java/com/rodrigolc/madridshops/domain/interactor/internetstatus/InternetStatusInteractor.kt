package com.rodrigolc.madridshops.domain.interactor.internetstatus

import com.rodrigolc.madridshops.domain.interactor.ErrorClosure


interface InternetStatusInteractor {
    fun execute(success: () -> Unit, error: ErrorClosure)
}
