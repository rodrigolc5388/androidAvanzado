package com.rodrigolc.madridshops.domain.interactor.getAllShops

import com.rodrigolc.madridshops.domain.interactor.ErrorCompletion
import com.rodrigolc.madridshops.domain.interactor.SuccessCompletion
import com.rodrigolc.madridshops.domain.model.Shops


interface GetAllShoptivitiesInteractor {
    fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion)
}
