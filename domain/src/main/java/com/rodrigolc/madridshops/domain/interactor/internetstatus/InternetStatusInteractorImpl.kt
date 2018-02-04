package com.rodrigolc.madridshops.domain.Interactor.internetstatus

import com.rodrigolc.madridshops.domain.Interactor.ErrorClosure
import com.rodrigolc.madridshops.domain.Interactor.internetStatus.InternetStatusInteractor

class InternetStatusInteractorImpl : InternetStatusInteractor {
    override fun execute(success: () -> Unit, error: ErrorClosure) {
        success()
    }
}