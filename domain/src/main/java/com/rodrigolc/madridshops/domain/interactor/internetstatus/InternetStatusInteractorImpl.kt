package com.rodrigolc.madridshops.domain.interactor.internetstatus

import com.rodrigolc.madridshops.domain.interactor.ErrorClosure

class InternetStatusInteractorImpl : InternetStatusInteractor {
    override fun execute(success: () -> Unit, error: ErrorClosure) {
        success()
    }
}