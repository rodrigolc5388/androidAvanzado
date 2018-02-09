package com.rodrigolc.madridshops.domain.interactor.getAllShops

import com.rodrigolc.madridshops.domain.interactor.ErrorCompletion
import com.rodrigolc.madridshops.domain.interactor.SuccessCompletion
import com.rodrigolc.madridshops.domain.model.Shoptivities


interface GetAllShoptivitiesInteractor {
    fun execute(success: SuccessCompletion<Shoptivities>, error: ErrorCompletion)
}
