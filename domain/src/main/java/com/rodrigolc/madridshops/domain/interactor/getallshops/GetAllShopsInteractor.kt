package com.rodrigolc.madridshops.domain.Interactor.getAllShops

import com.rodrigolc.madridshops.domain.Interactor.CodeClosure
import com.rodrigolc.madridshops.domain.Interactor.ErrorClosure


interface GetAllShopsInteractor {
    fun execute(success: CodeClosure, error: ErrorClosure)
}
