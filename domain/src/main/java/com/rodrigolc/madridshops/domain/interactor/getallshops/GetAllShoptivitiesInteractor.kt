package com.rodrigolc.madridshops.domain.interactor.getAllShops

import com.rodrigolc.madridshops.domain.interactor.ErrorCompletion
import com.rodrigolc.madridshops.domain.interactor.SuccessCompletion
import com.rodrigolc.madridshops.domain.model.Shoptivities
import com.rodrigolc.madridshops.utils.SectionType


interface GetAllShoptivitiesInteractor {
    fun execute(success: SuccessCompletion<Shoptivities>, error: ErrorCompletion)
    fun executeForType(type: SectionType, success: SuccessCompletion<Shoptivities>, error: ErrorCompletion)
}
